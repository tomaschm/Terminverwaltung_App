package at.main.ghwt.terminverwaltung_app.pkgData;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Martin on 18.01.2018.
 */

public class BenutzerController extends AsyncTask<Object, Void, String> {
    private static final String URI_FIX = "http://192.168.192.243:8080/TerminVerwaltung/webresources/";

    BufferedReader reader = null;
    BufferedWriter writer = null;
    String response = null;
    URL url = null;
    Gson gson = new Gson();

    protected String doInBackground(Object... command) {
        try {
            if (command[0].equals("GET")) {
                if (command[1].equals("BenutzerList")) {
                    url = new URL(URI_FIX + command[1]);
                    URLConnection conn = url.openConnection();

                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    response = sb.toString();
                    reader.close();
                }
            } else if (command[0].equals("PUT")) { //put ist das update
                //aber vorsicht ursprüngliche post methode!!!
                if (command[1].equals("Benutzer")) {
                    String updateBenutzer = gson.toJson(command[2]);
                    url = new URL(URI_FIX + command[1]);
                    HttpURLConnection httpUrlConnection = (HttpURLConnection) url.openConnection();
                    httpUrlConnection.setDoOutput(true);
                    httpUrlConnection.setRequestMethod("POST");
                    httpUrlConnection.setRequestProperty("Content-Type", "application/json");

                    byte[] outputBytes = updateBenutzer.getBytes("UTF-8");
                    httpUrlConnection.setRequestProperty("Content-Length", Integer.toString(outputBytes.length));
                    OutputStream os = httpUrlConnection.getOutputStream();
                    os.write(outputBytes);

                    reader = new BufferedReader(new InputStreamReader(httpUrlConnection.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }

                    response = sb.toString();

                    os.flush();
                    os.close();
                    reader.close();
                    httpUrlConnection.disconnect();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return response;
    }
}

