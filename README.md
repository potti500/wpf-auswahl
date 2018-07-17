# Auswahl der Wahlpflichtfächer
Beispielprozess zur elektronischen Wahlpflichtfach Auswahl mit Hilfe der BPMN-Process-Engine Camunda

## Einordnung des Themas


## Abgrenzung und Beschreibung

## Erläuterung der Modellierungsentscheidungen

## Reflexion 

## Potenzielle Verknüpfungen

Auszug aus dem Formular "wpfs-eintragen.html"

```
<label>WPF1</label> 
   <input  
            cam-variable-name="fach1"
            cam-variable-type="String"
            type="text" placeholder="Wahlpflichtfach eingeben" />

<label>CP1</label> 
   <input  
            cam-variable-name="cp1"
            cam-variable-type="Long"
            type="number" placeholder="Credit Points" />
```

Auszug aus der E-Mail Java Klasse "EintragenErinnerung.java"

```
public class EintragenErinnerung implements JavaDelegate {

  //Hier müssen die Einstellungen für den E-Mailservice definiert werden. 
  private static final String HOST = "smtp.gmail.com";
  private static final String USER = "demodemo1234512345@gmail.com";
  private static final String PWD = "xxxxxxxxxxx";

  
  public void execute(DelegateExecution execution) throws Exception {
	  
	  

	  //Empfänger E-Mail Adresse eintragen
	  String recipient = "pahlf@th-brandenburg.de";
     //Nachricht 
      String etext = "Sehr geehrter Herr Prof. Johannsen, \n\n"
		+ "wir bitten Sie hiermit um die baldige Eintragung der Wahlpflichtfächer in das entsprechende Portal.\n\n"
		+ "Mit freundlichen Grüßen\nDas Prüfungsamt";
      
      //Port und Daten für das versenden der Mail
      Email email = new SimpleEmail();
      email.setCharset("utf-8");
      email.setHostName(HOST);
      email.setSmtpPort(465);
      email.setAuthentication(USER, PWD);
      email.setSSL(true);
      
      try {
        //Absender & Betreff
          email.setFrom("pruefungsamt@th-brandenburg.org");
          email.setSubject("Reminder Wahlpflichtmodule");
          email.setMsg(etext);

          email.addTo(recipient);

          email.send();
        
        } catch (Exception e) {         
        }
}
}

```
Auszug aus dem Formular "wpfs-auswaehlen.html", Zählen der Fächer

```
    <script>
$(document).ready(function(){

    var $checkboxes = $('#zaehlen td input[type="checkbox"]');
        
    $checkboxes.change(function(){
        var countCheckedCheckboxes = $checkboxes.filter(':checked').length;
        $('#count-checked-checkboxes').text(countCheckedCheckboxes); 
        $('#anzahl-wpfs').val(countCheckedCheckboxes);
    });
});
    </script>

```

Auszug aus dem Formular "wpfs-auswaehlen.html", Summieren der Credit Points

```
<script>
$(document).ready(function(){
    var sum = 0;

    $('#zaehlen table tr').each(function(){
    	var row = $(this);
        $(this).find('.checkbox').on('change',function() {
    	    if ($(this).is(':checked')) {
    	    	sum += Number(row.find('.anzahlcp').val());
            } else {
				sum -= Number(row.find('.anzahlcp').val());
            }
			$(".total").val(sum);
        })
    });
});
</script>


```

Auszug aus der E-Mail Java Klasse "AnmeldungError.java"

```

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

```


Auszug aus dem Formular "wpfs-raum.html"

```
[....]
<label>Raum</label>
<div class="input-group date" id="raum">
<input class="form-control"
cam-variable-name="raum"
cam-variable-type="Long"/><span class="input-group-addon">
<span class="fa fa-map-marker"></span></span>
[....]

```
