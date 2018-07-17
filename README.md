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

![Alt text](/abbildungen/Eintragen.png?raw=true "Formular WPF eintragen")

Wird der Studiendekan nicht innerhalb 14 Tagen tätig und trägt die Wahlpflichtfächer in das Formular ein, wird eine Erinnerungsmail verschickt (Task: Erinnerung senden). Sind die Fächer inkl. der Credit Points eingetragen, wird im nächsten Schritt die Infoveranstaltung (Task: Infoveranstaltung planen) geplant (siehe hierzu Kapitel 2.2. Infoveranstaltung). Nach der gehaltenen Infoveranstaltung können die Studierx über ein Formular die gewünschten Wahlpflichtfächer auswählen (Task: WP-Fächer auswählen) und sich für diese anmelden. 

![Alt text](/abbildungen/Auswaehlen.png?raw=true "Fächer auswählen")

Nach dem der Studierx seine Fächer ausgewählt, sein Semester und seine Matrikelnummer eingetragen hat, wird die Zulassung durch die DMN (Task: Zulassung für WP-Fächer prüfen) geprüft (siehe hierzu Kapitel 2.3. Entscheidung). Ist die Prüfung nicht erfolgreich, wird der Studierx per E-Mail benachrichtigt (Task: Studierx benachrichtigen) und muss seine Eingabe bzw. Auswahl korrigieren. Sobald die Prüfung der Zulassung erfolgreich ist, erhält der Studierx eine E-Mail (Prüfungsanmeldung durchführen), dass er sich erfolgreich für die Wahlpflichtfächer eingeschrieben hat. Die Wahlpflichtfächer sind somit angemeldet und der Prozess ist beendet. 

### Infoveranstaltung.cmmn

![Alt text](/abbildungen/Infoveranstaltung.png?raw=true "Infoveranstaltung CMMN")

Der Prozess beginnt mit dem “CasePlanModel” (Termin und Raum festlegen). Dabei stimmt der Studiendekan mit den jeweiligen Professx (Task: Termin mit Professoren absprechen) einen Termin ab und wählt anschließend den Raum (Task: Raum auswählen) über ein Formular aus. Optional kann es vorkommen, dass für bestimmte Wahlpflichtmodule externe Dozenten (Task: externe Dozenten einladen) eingeladen werden müssen. Abschließend informiert der Studiendekan die Professx (Task: Professx benachrichtigen) sowie die Studierx (Task: Studierx benachrichtigen) per E-Mail über die Veranstaltung. Der Meilenstein (Planung abgeschlossen) ist erreicht. Anschließend geht es mit dem “CasePlanModel” (Wahlpflichtfächer vorstellen) weiter. In der Veranstaltung werden nun alle Fächer vorgestellt (Task: Inhalt erklären), die Dauer für die Vorstellung beträgt max. 15 Minuten pro Fach. Sollten Fragen seitens der Studierx aufkommen, können optional Fragen beantwortet (Task: Fragen beantworten) werden. Der Studiendekan stellt anschließend die Präsentation der Veranstaltung in Moodle (Task: Präsentation hochladen) und gibt die Frist bis wann sich entschieden werden muss (Task: Wahlfrist bekannt geben), bekannt. Der Meilenstein (WP-Fächer vorgestellt) ist erreicht und der Prozess ist beendet. 

### Entscheidung.dmn

![Alt text](/abbildungen/Entscheidung.dmn.png?raw=true "Entscheidung DMN")

Die Entscheidung ist in den BPMN-Prozess eingebunden. Der Beginn ist durch die vollständige Abarbeitung der User-Task WP-Fächer auswählen definiert. In das Formular dieser Task werden die notwendigen Daten eingegeben, welche die DMN-Entscheidungstabelle verarbeitet. Die Input-Variablen sind also in diesem Fall Anmeldedatum, Semester, Anzahlfaecher und SummeCP. Unser Prozess beschränkt sich auf die Auswahl und Anmeldung für die Wahlpflichtfächer im Master-Studiengang Wirtschaftsinformatik. Für die unterschiedlichen Möglichkeiten von Werten der Input-Variablen wurden nun Regeln zur Entscheidung gebildet. Der erste Wert der geprüft wird ist das Datum der Anmeldung bzw. der Auswahl durch den Studierenden. Je nach Semester, zwei oder drei, gelten unterschiedliche Fristen für diesen Vorgang, ebenso gilt laut Prüfungsordnung eine unterschiedliche Anzahl von Credit Points und Fächern, die belegt werden müssen. Für eine erfolgreiche Anmeldung gibt es je Semester zwei Möglichkeiten. Die jeweils zweite gültige Variante kommt durch Wahlpflichtfächer zustande, die zweigeteilt sind, also aus zwei Modulen mit je drei Credit Points bestehen und nur zusammen ein gültiges Wahlpflichtfach mit sechs Credit Points bilden. So ergeben sich auch weitere Möglichkeiten anhand der Anzahl der auszuwählenden Wahlpflichtfächer. Im zweiten Semester müssen dementsprechend 2 bzw. 3 Fächer mit insgesamt 12 Credit Points gewählt werden und im dritten Semester dann 3 bzw. 4 mit insgesamt 18 Credit Points. Anhand der festgeschriebenen Regel wird dann die Gültigkeit der Eingaben geprüft und mittels einer Variablen im Boolean Format ausgegeben, also bei gültiger Eingabe ein “true” und bei einer ungültigen Kombination ein “false”.    


## Erläuterung der Modellierungsentscheidungen

Die Erläuterung der fachlichen und technischen Modellierungsentscheidungen erfolgt pro Modell.

### BPMN

Die Aktivität “WP-Fächer eintragen” ist als User-Task modelliert, da hier der Studiendekan als als “menschlicher Akteur” tätig werden muss, um die Wahlpflichtfächer und Credit Points in das Formular einzutragen. Das HTML-Formular wurde im User-Task im Feld “Form Key” eingebunden (embedded:app:forms/wpfs-eintragen.html). In folgendem Auszug sind die verwendeten Variablen (cam-variable-name) sowie der Datentyp (cam-variable-type) ersichtlich. Für die Fächer wurde fach + fortlaufende Nummer verwendet (fach1, fach2, etc.). Die Fächer wurden mit dem Datentyp “String” deklariert, da es sich bei den Fächernamen um eine Zeichenkette handelt. Die Credit Points hingegen wurden mit dem Datentyp “Long” deklariert, weil es sich hierbei um Zahlen handelt und “Long” die Vorgabe von Camunda ist. 

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

Das vollständige Formular liegt unter: src/main/webapps/forms. 

An die Task “WP-Fächer eintragen” ist ein abbrechendes Zeit-Zwischeneregnis angeheftet. Ein angeheftetes abbrechendes Zeit-Zwischeneregnis tritt ein, wenn entweder ein Zeitpunkt erreicht ist oder eine Frist abgelauft ist oder sich ein Zeitzyklus wiederholt. In unserem Falle haben wir eine Frist von 14 Tagen hinterlegt. Dafür wird der Typ (Timer Definition Type) als “Duration” festgelegt und die Zeit mit “P14D” definiert. “P” steht dabei für Perion (Zeitraum) und “D” steht für Days (Tage). Nach Ablauf der Frist wird der Studiendekan per E-Mail benachrichtigt. Dafür wurde in den “Send Task” (Erinnerung senden) eine Java Klasse implementiert. Ein Auszug, aus der Java Klasse, ist im Folgenden zu sehen. 

Auszug aus der E-Mail Java Klasse "EintragenErinnerung.java"

```
public class EintragenErinnerung implements JavaDelegate {

  //Hier müssen die Einstellungen für den E-Mailservice definiert werden. 
  private static final String HOST = "smtp.gmail.com";
  private static final String USER = "beispiel@gmail.com";
  private static final String PWD = "xxxxxxxxxxx";

  
  public void execute(DelegateExecution execution) throws Exception {
	  
	  

	  //Empfänger E-Mail Adresse eintragen
	  String recipient = "beispiel@th-brandenburg.de";
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
Über den Task “Infoveranstaltung planen” wurde unser CMMN Prozess eingebunden. Hierbei handelt es sich um eine “Call Activity” und nicht um einen klassischen Subprozess. BPMN 2.0 unterscheidet diese beiden Aktivitäten. Der Unterschied hierbei ist, dass die Call Activity auf einen externen Prozess verweist. Ein Subprozess hingegen wäre innerhalb der Prozessdefinition. Sobald der Prozess die Call Activity erreicht, wird eine neue Prozessinstanz erstellt, welche den externen Subprozess ausführt. Der CMMN Prozess ist in Kapitel 2.2 näher beschrieben. 

Im nächsten Schritt wählen die Studierx die Wahlpflichtfächer aus, weshalb der Task “WP-Fächer auswählen” als User Task modelliert ist. Hier ist ebenfalls ein Formular (HTML / CSS / JavaScript) eingebunden. Die Fächer und Credit Points wurden vom Studiendekan in der Task “WP-Fächer eintragen” eingetragen. Folgende GIF soll die Funktionen des Formulars darstellen. 

![Alt text](/abbildungen/Auswaehlen.gif?raw=true "WPF Auswählen GIF")

Die Studierx können über Checkboxen die gewünschten Fächer auswählen. Folgendes JavaScript zählt, wie viele Fächer ausgewählt wurden. Dieser Vorgang ist für die Zulassungsprüfung relevant. 

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

Des Weiteren werden über folgendes JavaScript die Credit Points gezählt und summiert. Das Script zählt pro Zeile in der Tabelle, welches Fach ausgewählt ist, und summiert die entsprechenden Credit Points. 

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

Auch dieser Vorgang ist für die Zulassungsprüfung relevant. Die Summe wird per Variable an die DMN Tabelle übergeben. Ferner wird per JavaScript ein Zeitstempel erstellt, welcher den aktuellen Zeitpunkt der Anmeldung der Fächer festhält und an die DMN Tabelle zur Prüfung weitergibt. Im darauffolgenden Task “Zulassung für WP-Fächer prüfen” kommt nun die DMN Tabelle zum Einsatz (Kapitel 2.3.). Nach dem DMN Task folgt ein exklusives Gateway. Gibt die DMN Entscheidung den Wert “false” aus (${antwort==false}), war die Prüfung nicht erfolgreich. Daraufhin folgt der Send Task (Studierx benachrichtigen) in dem erneut eine Mail Java Klasse implementiert ist. Die Java Klasse verschickt eine E-Mail an den Studierx mit der bitte die Eingaben zu prüfen. In der E-Mail sind auch die ausgewählten Fächer + Credit Points aufgelistet. Diese werden über die definierte Variable eingebunden (siehe folgender Ausschnitt). 

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

Ist die Zulassung erfolgreich (${antwort==true}) folgt der Send Task (Prüfungsanmeldung durchführen). Dadurch erhält der Studierx eine E-Mail (hier ist ebenfalls eine Java Klasse implementiert) mit einer Liste der ausgewählten Fächer, sowie die Bestätigung, dass er sich erfolgreich für die Fächer eingetragen hat. Der Prozess ist anschließend beendet. 

### CMMN

Die Tasks “Termin mit Professoren absprechen” und “Raum auswählen” wurden als blockierende sowie notwendige “HumanTasks” modelliert, da diese Aktivitäten erst beendet werden müssen, bevor die Fallbearbeitung fortfahren kann. Im Task “Raum auswählen” ist zusätzlich ein Formular hinterlegt in dem der Studiendekan das Datum, die Uhrzeit und den Raum der Veranstaltung eingibt. Der integrierte “Task Listener” wartet bis der Task fertig ist und aktiviert anschließend die implementierte Java Klasse in der die E-Mail mit den gerade eingegebenen Daten an die Professx und Studierx verschickt wird. 

![Alt text](/abbildungen/Raum.png?raw=true "Auswahl Raum und Datum")

Das Formular (Embedded task forms) besteht aus HTML/CSS und kann direkt in der Camunda Tasklist angezeigt werden. Damit dies erfolgen kann, muss im "UserTask" des Prozess Modells darauf hin verwiesen werden, z. B. "embedded:app:forms/wpfs-raum.html" im Feld "Form Key". Wichtig dabei ist, dass sich das HTML Formular in den Tags <form>...</form> befindet. Wichtig ist auch, dass für die Input Felder eine Variable (cam-variable-name) sowie ein typ (cam-variable-type) vergeben wird, siehe hierzu den Auszug aus o. g. Formular mit dem name "raum" sowie dem typ "Long". Die enthaltenen Klassen sind für das Styling und verweisen auf das Framework "Bootstrap” bzw. auf die Icons von “Fontawesome”. 

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

Das vollständige Formular liegt unter: src/main/webapps/forms. Weitere Informationen zu "Embedded task forms" sind hier verfügbar: https://docs.camunda.org/manual/7.9/user-guide/task-forms/#embedded-task-forms  

Die Task “externe Dozenten einladen” wurde als “Discretionary” Task definiert, da dieser nicht ausgeführt werden muss, sondern nach Einschätzung des Studiendekan ausgeführt werden kann. Die Tasks “Professx benachrichtigen” und “Studierx benachrichtigen” wurden als nicht blockierende "HumanTasks" modelliert. Diese Tasks werden vom Studiendekan manuell (Dreieck Symbol im Task) ausgeführt und stoppen dabei den Sequenzfluss nicht. Der anschließende Meilenstein (Planung abgeschlossen) symbolisiert, dass ein Ziel innerhalb eines Falles erreicht ist. Das Erreichen des Meilensteins im Sequenzfluss bedeutet, dass die Phase “Termin und Raum festlegen” abgeschlossen ist. Der Meilenstein selbst repräsentiert also nur den Bearbeitungsfortschritt, ohne selbst einen Arbeitsfortschritt zu assoziieren. 

Die Tasks “Inhalt erklären” sowie “Wahlfrist bekannt geben” wurden als blockierende sowie notwendige “HumanTasks” modelliert, da diese Aktivitäten notwendig sind, sowie erst beendet werden müssen, damit die Fallbearbeitung fortfahren kann. Der Task “Inhalt erklären” wird zudem mehrmals durchlaufen (pro Fach) und ist deshalb mit der Raute (Repetition) spezifiziert. Zusätzlich wurde der Task “Fragen beantworten" als “Discretionary” Task definiert, da dieser nur ausgeführt wird, wenn Fragen der Studierx aufkommen. Abschließend führt der Studiendekan den nicht blockierenden “HumanTask” “Präsentation hochladen” manuell aus. Der Prozess endet im Meilenstein “WP-Fächer vorgestellt”. 

### DMN

Die mit wichtigsten Punkte, die bei der Erstellung einer DMN-Entscheidungstabelle beachtet werden müssen sind die Eindeutigkeit und die Komplettheit. Das bedeutet, dass zum einen alle möglichen Kombinationen bei allen Variablen verwendet werden müssen, und zum anderen, dass es für jede Möglichkeit nur genau einen Ausgabewert gibt, da in dem von uns erstellten Modell die Unique Hit Policy verwendet wird.  Die Datenformate der Variablen entsprechen denen, der jeweiligen Eingaben im vorgelagerten Formular. Für das Anmeldedatum wurde Date verwendet, für die anderen Werte jeweils Long. 


## Reflexion von Schwachstellen

Die Lauffähigkeit des Prozesses auf dem Hochschulserver hat leider nicht funktioniert. Dabei sind diverse Fehlermeldungen aufgetreten. Dies könnte damit zusammenhängen, dass wir lokal mit der Camunda BPM Version 7.9 gearbeitet haben, auf dem Server jedoch die Version 7.8 läuft. Eine der Hauptfehlerquellen war, dass Camunda BPM auf dem Hochschulserver unsere Formulare nicht gefunden hat (Context path NULL). Diesbezüglich gibt es auch schon “Bug Reports” und Foreneinträge. Eine Lösung konnte aber nicht eruiert werden. 
Bug Report: https://app.camunda.com/jira/browse/CAM-5256 
Foreneintrag: https://forum.camunda.org/t/embedded-form-failure/2473 

## Optionen für Verbesserungen

In der DMN Tabelle (WPA-Pruefung) könnte ein zusätzliches Output Feld als “string” implementiert werden, so dass eine sprechende Rückmeldung nach erfolgreicher Prüfung als Nachricht ausgegeben werden kann. Die folgende Abbildung zeigt, wie dies aussehen könnte.

![Alt text](/abbildungen/Optional.png?raw=true "Option für Verbesserungen DMN")


## Potenzielle Verknüpfungen

Der aktuelle Prozess ist für den Masterstudiengang Wirtschaftsinformatik der THB ausgelegt. Dieser könnte jedoch durch kleine Änderungen und Anpassungen je nach Studien- und Prüfungsordnung der einzelnen Studiengänge angepasst werden. Des Weiteren wäre es sinnvoll, eine Schnittstelle zum HIS-Portal (Hochschulinformationsportal) der THB herzustellen um dort die Eintragungen für die Wahlpflichtfächer vorzunehmen. Für die Authentifizierung der Studierx wäre zudem eine Verknüpfung mit dem Identity Management (IdM) der THB empfehlenswert.






