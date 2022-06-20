package com.google.sps.servlets;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ArrayList<String> list = new ArrayList<>();
    list.add("Bonjour!");
    list.add("Hola!");
    list.add("Ciao!");

    response.setContentType("application/json;");
    Gson gson = new Gson();
    String json = gson.toJson(list);
    response.getWriter().println(json);
  }
}
