package org.camunda.bpm.getstarted.loanapproval;


import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.delegate.TaskListener;


public class RaumInfo implements TaskListener {

  //TODO: Set Mail Server Properties
  private static final String HOST = "smtp.gmail.com";
  private static final String USER = "demodemo1234512345@gmail.com";
  private static final String PWD = "google12345";

  
  public void notify(DelegateTask delegateTask)  {
	  String Datum = (String) delegateTask.getVariable("datum");
	  String Uhrzeit = (String) delegateTask.getVariable("zeit");
	  Long Raum = (Long) delegateTask.getVariable("raum");
	  

	  
	  String recipient = "pahlf@th-brandenburg.de";
      String etext = 
    		  "Sehr geehrte Professoren und Studierende, \n\n"
      		  		+ "die Infoveranstaltung zu den Wahlpflichtfächern findet am "+Datum+" um "+Uhrzeit+" Uhr in Raum "+Raum+" statt.\n\n"
      		  		+ "Mit freundlichen Grüßen\nA. Johannsen";
      
      
      Email email = new SimpleEmail();
      email.setCharset("utf-8");
      email.setHostName(HOST);
      email.setSmtpPort(465);
      email.setAuthentication(USER, PWD);
      email.setSSL(true);
      
      try {
          email.setFrom("pruefungsamt@th-brandenburg.org");
          email.setSubject("Infoveranstaltung Wahlpflichtmodule");
          email.setMsg(etext);

          email.addTo(recipient);

          email.send();
          

        } catch (Exception e) {
         
        }
      
    }

}
