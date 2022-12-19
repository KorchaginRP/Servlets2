package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/math")
public class ServletMath extends HttpServlet {
    Gson gson= new GsonBuilder().setPrettyPrinting().create();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuffer jb = new StringBuffer();
        String line;
        try {
            BufferedReader reader = request.getReader();
            while ((line=reader.readLine())!= null){
                jb.append(line);
            }
        } catch (Exception e) {
            System.out.println("Error");
        }
        JsonObject jobj=gson.fromJson(String.valueOf(jb), JsonObject.class);
        request.setCharacterEncoding("UTF-8");
        double a = jobj.get("a").getAsDouble();
        double b = jobj.get("b").getAsDouble();
        String math = jobj.get("math").getAsString();
        double c;

        if (math.equals( "+")){
            c=a+b;
        }
        else  if (math.equals( "-")) {
            c=a-b;
        }
        else if (math.equals( "/")){
            c=a/b;
        }
        else{
            c=a*b;
        }

        response.setContentType("application/json; charset=utf-8");
        PrintWriter pw = response.getWriter();

        pw.print(gson.toJson(c));
    }


}
