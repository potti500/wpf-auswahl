# Auswahl der Wahlpflichtfächer
Beispielprozess zur elektronischen Wahlpflichtfach Auswahl mit Hilfe der BPMN-Process-Engine Camunda

## Einordnung des Themas

Die Belegung der Wahlpflichtfächer ist zentraler Bestandteil der Studien- und Prüfungsordnung der jeweiligen Studiengänge an der Technische Hochschule Brandenburg (THB). Der Beispielprozess zur elektronischen Wahlpflicht-Auswahl mit Hilfe der Camunda Process Engine lässt sich in der Prozesslandschaft der THB unter "Digitales Campusmanagement" einordnen. Hier finden sich Prozesse im Bereich des studentischen Lebenszyklus (Studierenden-, Kurs und Prüfungsverwaltung, etc.) sowie weiterer Aufgabenfelder der Hochschulverwaltung wieder. Im Zuge der Entlastung von Hochschulverwaltungen sowie internetbasierten Hochschulportale gewinnt dies immer mehr an Bedeutung. 

## Abgrenzung und Beschreibung

Der Prozess zur elektronischen Wahlpflichtfach Auswahl an der THB ist in BPMN (Business Process Model and Notation) modelliert und beinhaltet ein CMMN (Case Management Model and Notation) zur Planung der Infoveranstaltung sowie eine DMN (Decision Model and Notation) Tabelle zur Zulassungsprüfung. Der Prozess wird im folgenden Kapitel pro Notation erläutert. 

### Wahlpflichtauswahl.bpmn 

![Alt text](/abbildungen/Wahlpflichtauswahl.png?raw=true "Wahlpflichtauswahl BPMN")

Der Hauptprozess ist als BPMN modelliert und zeigt den Prozess von der Wahlpflicht Eintragung durch den Studiendekan, über die Wahlpflichtauswahl der Studierx bis hin zur Prüfungsanmeldung. 

Der Prozess der elektronischen Wahlpflichtauswahl ist für die Organisation “Technische Hochschule Brandenburg” (Pool) modelliert und beinhaltet die drei Rollen (Swimlanes), Studiendekan, Prüfungsamt (Process Engine) und Studierx. 

Der Prozess startet gegen Ende des vorherigen Semesters. Der aktuelle Zeitpunkt liegt im Ermessen des Studiendekans, weshalb hier kein spezieller Zeitpunkt definiert werden konnte. Anschließend trägt der Studiendekan über ein Formular (Task: WP-Fächer eintragen) alle für das kommende Semester zur Verfügung stehenden Wahlpflichtfächer mit dem Namen und der Anzahl der Credit Points ein. Für unseren Prototypen wurden Eingabefelder für vier Fächer definiert. 

Wird der Studiendekan nicht innerhalb 14 Tagen tätig und trägt die Wahlpflichtfächer in das Formular ein, wird eine Erinnerungsmail verschickt (Task: Erinnerung senden). Sind die Fächer inkl. der Credit Points eingetragen, wird im nächsten Schritt die Infoveranstaltung (Task: Infoveranstaltung planen) geplant (siehe hierzu Kapitel 2.2. Infoveranstaltung). Nach der gehaltenen Infoveranstaltung können die Studierx über ein Formular die gewünschten Wahlpflichtfächer auswählen (Task: WP-Fächer auswählen) und sich für diese anmelden. 

Nach dem der Studierx seine Fächer ausgewählt, sein Semester und seine Matrikelnummer eingetragen hat, wird die Zulassung durch die DMN (Task: Zulassung für WP-Fächer prüfen) geprüft (siehe hierzu Kapitel 2.3. Entscheidung). Ist die Prüfung nicht erfolgreich, wird der Studierx per E-Mail benachrichtigt (Task: Studierx benachrichtigen) und muss seine Eingabe bzw. Auswahl korrigieren. Sobald die Prüfung der Zulassung erfolgreich ist, erhält der Studierx eine E-Mail (Prüfungsanmeldung durchführen), dass er sich erfolgreich für die Wahlpflichtfächer eingeschrieben hat. Die Wahlpflichtfächer sind somit angemeldet und der Prozess ist beendet. 

### Infoveranstaltung.cmmn

![Alt text](/abbildungen/Infoveranstaltung.png?raw=true "Infoveranstaltung CMMN")

Der Prozess beginnt mit dem “CasePlanModel” (Termin und Raum festlegen). Dabei stimmt der Studiendekan mit den jeweiligen Professx (Task: Termin mit Professoren absprechen) einen Termin ab und wählt anschließend den Raum (Task: Raum auswählen) über ein Formular aus. Optional kann es vorkommen, dass für bestimmte Wahlpflichtmodule externe Dozenten (Task: externe Dozenten einladen) eingeladen werden müssen. Abschließend informiert der Studiendekan die Professx (Task: Professx benachrichtigen) sowie die Studierx (Task: Studierx benachrichtigen) per E-Mail über die Veranstaltung. Der Meilenstein (Planung abgeschlossen) ist erreicht. Anschließend geht es mit dem “CasePlanModel” (Wahlpflichtfächer vorstellen) weiter. In der Veranstaltung werden nun alle Fächer vorgestellt (Task: Inhalt erklären), die Dauer für die Vorstellung beträgt max. 15 Minuten pro Fach. Sollten Fragen seitens der Studierx aufkommen, können optional Fragen beantwortet (Task: Fragen beantworten) werden. Der Studiendekan stellt anschließend die Präsentation der Veranstaltung in Moodle (Task: Präsentation hochladen) und gibt die Frist bis wann sich entschieden werden muss (Task: Wahlfrist bekannt geben), bekannt. Der Meilenstein (WP-Fächer vorgestellt) ist erreicht und der Prozess ist beendet. 

### Entscheidung.dmn

![Alt text](/abbildungen/Entscheidung.png?raw=true "Entscheidung DMN")

Die Entscheidung ist in den BPMN-Prozess eingebunden. Der Beginn ist durch die vollständige Abarbeitung der User-Task WP-Fächer auswählen definiert. In das Formular dieser Task werden die notwendigen Daten eingegeben, welche die DMN-Entscheidungstabelle verarbeitet. Die Input-Variablen sind also in diesem Fall Anmeldedatum, Semester, Anzahlfaecher und SummeCP. Unser Prozess beschränkt sich auf die Auswahl und Anmeldung für die Wahlpflichtfächer im Master-Studiengang Wirtschaftsinformatik. Für die unterschiedlichen Möglichkeiten von Werten der Input-Variablen wurden nun Regeln zur Entscheidung gebildet. Der erste Wert der geprüft wird ist das Datum der Anmeldung bzw. der Auswahl durch den Studierenden. Je nach Semester, zwei oder drei, gelten unterschiedliche Fristen für diesen Vorgang, ebenso gilt laut Prüfungsordnung eine unterschiedliche Anzahl von Credit Points und Fächern, die belegt werden müssen. Für eine erfolgreiche Anmeldung gibt es je Semester zwei Möglichkeiten. Die jeweils zweite gültige Variante kommt durch Wahlpflichtfächer zustande, die zweigeteilt sind, also aus zwei Modulen mit je drei Credit Points bestehen und nur zusammen ein gültiges Wahlpflichtfach mit sechs Credit Points bilden. So ergeben sich auch weitere Möglichkeiten anhand der Anzahl der auszuwählenden Wahlpflichtfächer. Im zweiten Semester müssen dementsprechend 2 bzw. 3 Fächer mit insgesamt 12 Credit Points gewählt werden und im dritten Semester dann 3 bzw. 4 mit insgesamt 18 Credit Points. Anhand der festgeschriebenen Regel wird dann die Gültigkeit der Eingaben geprüft und mittels einer Variablen im Boolean Format ausgegeben, also bei gültiger Eingabe ein “true” und bei einer ungültigen Kombination ein “false”.    


## Erläuterung der Modellierungsentscheidungen

## Reflexion von Schwachstellen

Die Lauffähigkeit des Prozesses auf dem Hochschulserver hat leider nicht funktioniert. Dabei sind diverse Fehlermeldungen aufgetreten. Dies könnte damit zusammenhängen, dass wir lokal mit der Camunda BPM Version 7.9 gearbeitet haben, auf dem Server jedoch die Version 7.8 läuft. Eine der Hauptfehlerquellen war, dass Camunda BPM auf dem Hochschulserver unsere Formulare nicht gefunden hat (Context path NULL). Diesbezüglich gibt es auch schon “Bug Reports” und Foreneinträge. Eine Lösung konnte aber nicht eruiert werden. 
Bug Report: https://app.camunda.com/jira/browse/CAM-5256 
Foreneintrag: https://forum.camunda.org/t/embedded-form-failure/2473 

## Optionen für Verbesserungen

In der DMN Tabelle (WPA-Pruefung) könnte ein zusätzliches Output Feld als “string” implementiert werden, so dass eine sprechende Rückmeldung nach erfolgreicher Prüfung als Nachricht ausgegeben werden kann. Die folgende Abbildung zeigt, wie dies aussehen könnte.


## Potenzielle Verknüpfungen

Der aktuelle Prozess ist für den Masterstudiengang Wirtschaftsinformatik der THB ausgelegt. Dieser könnte jedoch durch kleine Änderungen und Anpassungen je nach Studien- und Prüfungsordnung der einzelnen Studiengänge angepasst werden. Des Weiteren wäre es sinnvoll, eine Schnittstelle zum HIS-Portal (Hochschulinformationsportal) der THB herzustellen um dort die Eintragungen für die Wahlpflichtfächer vorzunehmen. Für die Authentifizierung der Studierx wäre zudem eine Verknüpfung mit dem Identity Management (IdM) der THB empfehlenswert.



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
