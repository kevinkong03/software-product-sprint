package com.google.sps.servlets;

import com.google.cloud.language.v1.Document;
import com.google.cloud.language.v1.LanguageServiceClient;
import com.google.cloud.language.v1.Sentiment;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

    // Get the value entered in the form.
    String textValue = request.getParameter("text-input");

    Document doc =
        Document.newBuilder().setContent(textValue).setType(Document.Type.PLAIN_TEXT).build();
    LanguageServiceClient languageService = LanguageServiceClient.create();
    Sentiment sentiment = languageService.analyzeSentiment(doc).getDocumentSentiment();
    float score = sentiment.getScore();
    languageService.close();

    // Print the value so you can see it in the server logs.
    System.out.println("You submitted: " + textValue);

    // Write the value to the response so the user can see it.
    //response.getWriter().println("You submitted: " + textValue);
    response.getWriter().println("<head><title>Submitted</title></head>");
    response.getWriter().println("<h2>I got your message!</h2>");
    if(score <= -0.5){
        response.getWriter().println("<p>Its emoji sentiment is &#128511;.</p>");
    }
    else if(score > -0.5 && score <= 0){
        response.getWriter().println("<p>Its emoji sentiment is &#128528;.</p>");
    }
    else if(score > 0 && score <= 0.5){
        response.getWriter().println("<p>Its emoji sentiment is &#128578;.</p>");
    }
    else{
        response.getWriter().println("<p>Its emoji sentiment is &#128522;.</p>");
    }
    response.getWriter().println("<a href=\"../\">Click to return home</a>");
  }
}
