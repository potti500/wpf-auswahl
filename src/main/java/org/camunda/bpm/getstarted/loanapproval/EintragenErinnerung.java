package org.camunda.bpm.getstarted.loanapproval;


import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class EintragenErinnerung implements JavaDelegate {

  //TODO: Set Mail Server Properties
  private static final String HOST = "smtp.gmail.com";
  private static final String USER = "demodemo1234512345@gmail.com";
  private static final String PWD = "google12345";

  
  public void execute(DelegateExecution execution) throws Exception {
	  

	  
	  String recipient = "pahlf@th-brandenburg.de";
      String etext = 
    		  "Sehr geehrter Herr Prof. Johannsen, \n\n"
        		  		+ "wir bitten Sie hiermit um die baldige Eintragung der Wahlpflichtfächer in das entsprechende Portal.\n\n"
        		  		+ "Mit freundlichen Grüßen \n\n Ihr Prüfungsamt";
      
      
      Email email = new SimpleEmail();
      email.setCharset("utf-8");
      email.setHostName(HOST);
      email.setSmtpPort(465);
      email.setAuthentication(USER, PWD);
      email.setSSL(true);
      
      try {
          email.setFrom("pruefungsamt@th-brandenburg.org");
          email.setSubject("Reminder Wahlpflichtmodule");
          email.setMsg(etext);

          email.addTo(recipient);

          email.send();
          

        } catch (Exception e) {
         
        }
      
    }

}
