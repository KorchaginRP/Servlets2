package ru.appline;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import ru.appline.logic.Model;
import ru.appline.logic.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/del")

public class ServletDel extends HttpServlet {
    private AtomicInteger counter = new AtomicInteger( 4);
    Model model = Model.getInstance();
    Gson gson= new GsonBuilder().setPrettyPrinting().create();

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

        int id = jobj.get("id").getAsInt();

        model.delete(id);
        counter.getAndDecrement();

        response.setContentType("application/json; charset=utf-8");
        PrintWriter pw = response.getWriter();

        pw.print(gson.toJson(model.getFromList()));
    }
    }

