package org.camunda.bpm.getstarted.loanapproval;


import java.util.Date;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class AnmeldungError implements JavaDelegate {

  //TODO: Set Mail Server Properties
  private static final String HOST = "smtp.gmail.com";
  private static final String USER = "demodemo1234512345@gmail.com";
  private static final String PWD = "google12345";

  
  public void execute(DelegateExecution execution) throws Exception {
	  Long cp1 = (Long) execution.getVariable("cp1");
	  Long cp2 = (Long) execution.getVariable("cp2");
	  Long cp3 = (Long) execution.getVariable("cp3");
	  Long cp4 = (Long) execution.getVariable("cp4");
	  String fach1 = (String) execution.getVariable("fach1");
	  String fach2 = (String) execution.getVariable("fach2");
	  String fach3 = (String) execution.getVariable("fach3");
	  String fach4 = (String) execution.getVariable("fach4");
	  Long semester = (Long) execution.getVariable("semester");
	  Long summecp = (Long) execution.getVariable("summecp");
	  Date Datum = (Date) execution.getVariable("anmeldedatum");
	  
	  String recipient = "pahlf@th-brandenburg.de";
      String etext = 
    		  " Sehr geehrter Studierende, \n\n"
    		  		+ "bei dem Versuch Sie für die Prüfung in den Wahlpflichtmodulen anzumelden ist leider ein Fehler aufgetreten.\n\n"
    		  		+ "Bitte Prüfen Sie ihre Eingaben und wiederholen den Vorgang.\n\n"
    		  		+"WP-Fach1: "+fach1+" - "+cp1+"CP\n"
    		  		+"WP-Fach2: "+fach2+" - "+cp2+"CP\n"
    		  		+"Summe CP für die Auswahl: "+summecp+" CP\n"
    		  		+"Anmeldung für Semester: "+semester+"\n\n"
    		  		+"Überprüfen Sie auch gegebenenfalls nochmals die Anmeldefrist\n\n"
    		  		+ "Mit freundlichen Grüßen,\nIhr Prüfungsamt";
      
      
      Email email = new SimpleEmail();
      email.setCharset("utf-8");
      email.setHostName(HOST);
      email.setSmtpPort(465);
      email.setAuthentication(USER, PWD);
      email.setSSL(true);
      
      try {
          email.setFrom("pruefungsamt@th-brandenburg.org");
          email.setSubject("Prüfungsanmeldung");
          email.setMsg(etext);

          email.addTo(recipient);

          email.send();
          

        } catch (Exception e) {
         
        }
      
    }

}
