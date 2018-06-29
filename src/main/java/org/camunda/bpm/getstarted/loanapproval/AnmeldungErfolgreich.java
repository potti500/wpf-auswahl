package org.camunda.bpm.getstarted.loanapproval;


import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class AnmeldungErfolgreich implements JavaDelegate {

  //TODO: Set Mail Server Properties
  private static final String HOST = "smtp.gmail.com";
  private static final String USER = "demodemo1234512345@gmail.com";
  private static final String PWD = "google12345";

  
  public void execute(DelegateExecution execution) throws Exception {
	  Long matrikel = (Long) execution.getVariable("matrikel");
	  String fach1 = (String) execution.getVariable("fach1");
	  String fach2 = (String) execution.getVariable("fach2");
	  String fach3 = (String) execution.getVariable("fach3");
	  String fach4 = (String) execution.getVariable("fach4");
	  
	  String recipient = "pahlf@th-brandenburg.de";
      String etext = 
    		  "Sehr geehrter Studierende mit der Matrikelnummer "+ matrikel +", \n\n"
    		  		+ "hiermit bestätigen wir ihen die erfolgreiche Prüfungsanmeldung für folgende Module:\n\n"
    		  		+ ""+fach1+"\n"
    		  		+ ""+fach2+"\n"
    		  		+ ""+fach3+"\n"
    		  		+ ""+fach4+"\n"
    		  		+ "Mit freundlichen Grüßen,\n Ihr Prüfungsamt";
      
      
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
