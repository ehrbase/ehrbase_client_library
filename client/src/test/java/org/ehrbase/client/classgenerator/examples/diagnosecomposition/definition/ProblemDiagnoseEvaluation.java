package org.ehrbase.client.classgenerator.examples.diagnosecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.generic.PartyProxy;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.util.List;
import org.ehrbase.client.annotations.Archetype;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.shareddefinition.Language;

@Entity
@Archetype("openEHR-EHR-EVALUATION.problem_diagnosis.v1")
public class ProblemDiagnoseEvaluation {
  @Path("/data[at0001]/items[at0002]/value|defining_code")
  private NameDesProblemsDerDiagnoseDefiningCode nameDesProblemsDerDiagnoseDefiningCode;

  @Path("/data[at0001]/items[at0009 and name/value='Freitextbeschreibung']/value|value")
  private String freitextbeschreibungValue;

  @Path("/data[at0001]/items[at0012 and name/value='Lokalisation']/value|value")
  private String lokalisationValue;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.anatomical_location.v1]")
  private List<AnatomischeLokalisationCluster> anatomischeLokalisation;

  @Path("/data[at0001]/items[at0077 and name/value='Datum des Auftretens/der Erstdiagnose']/value|value")
  private TemporalAccessor datumDesAuftretensDerErstdiagnoseValue;

  @Path("/data[at0001]/items[at0003 and name/value='Feststellungsdatum']/value|value")
  private TemporalAccessor feststellungsdatumValue;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.diagnose_details.v0]")
  private DiagnosedetailsCluster diagnosedetails;

  @Path("/data[at0001]/items[openEHR-EHR-CLUSTER.etiology.v1]")
  private AtiopathogeneseCluster atiopathogenese;

  @Path("/data[at0001]/items[at0030]/value|value")
  private TemporalAccessor datumZeitpunktDerGenesungValue;

  @Path("/data[at0001]/items[at0046]")
  private List<Cluster> status;

  @Path("/data[at0001]/items[at0069 and name/value='Diagnoseerläuterung']/value|value")
  private String diagnoseerlauterungValue;

  @Path("/protocol[at0032]/items[at0070 and name/value='Letztes Dokumentationsdatum']/value|value")
  private TemporalAccessor letztesDokumentationsdatumValue;

  @Path("/protocol[at0032]/items[at0071]")
  private List<Cluster> erweiterung;

  @Path("/subject")
  private PartyProxy subject;

  @Path("/language")
  private Language language;

  @Path("/data[at0001]/items[at0073]/value")
  @Choice
  private ProblemDiagnoseDiagnostischeSicherheitChoice diagnostischeSicherheit;

  @Path("/data[at0001]/items[at0005]/value")
  @Choice
  private ProblemDiagnoseSchweregradChoice schweregrad;

  public void setNameDesProblemsDerDiagnoseDefiningCode(
      NameDesProblemsDerDiagnoseDefiningCode nameDesProblemsDerDiagnoseDefiningCode) {
     this.nameDesProblemsDerDiagnoseDefiningCode = nameDesProblemsDerDiagnoseDefiningCode;
  }

  public NameDesProblemsDerDiagnoseDefiningCode getNameDesProblemsDerDiagnoseDefiningCode() {
     return this.nameDesProblemsDerDiagnoseDefiningCode ;
  }

  public void setFreitextbeschreibungValue(String freitextbeschreibungValue) {
     this.freitextbeschreibungValue = freitextbeschreibungValue;
  }

  public String getFreitextbeschreibungValue() {
     return this.freitextbeschreibungValue ;
  }

  public void setLokalisationValue(String lokalisationValue) {
     this.lokalisationValue = lokalisationValue;
  }

  public String getLokalisationValue() {
     return this.lokalisationValue ;
  }

  public void setAnatomischeLokalisation(
      List<AnatomischeLokalisationCluster> anatomischeLokalisation) {
     this.anatomischeLokalisation = anatomischeLokalisation;
  }

  public List<AnatomischeLokalisationCluster> getAnatomischeLokalisation() {
     return this.anatomischeLokalisation ;
  }

  public void setDatumDesAuftretensDerErstdiagnoseValue(
      TemporalAccessor datumDesAuftretensDerErstdiagnoseValue) {
     this.datumDesAuftretensDerErstdiagnoseValue = datumDesAuftretensDerErstdiagnoseValue;
  }

  public TemporalAccessor getDatumDesAuftretensDerErstdiagnoseValue() {
     return this.datumDesAuftretensDerErstdiagnoseValue ;
  }

  public void setFeststellungsdatumValue(TemporalAccessor feststellungsdatumValue) {
     this.feststellungsdatumValue = feststellungsdatumValue;
  }

  public TemporalAccessor getFeststellungsdatumValue() {
     return this.feststellungsdatumValue ;
  }

  public void setDiagnosedetails(DiagnosedetailsCluster diagnosedetails) {
     this.diagnosedetails = diagnosedetails;
  }

  public DiagnosedetailsCluster getDiagnosedetails() {
     return this.diagnosedetails ;
  }

  public void setAtiopathogenese(AtiopathogeneseCluster atiopathogenese) {
     this.atiopathogenese = atiopathogenese;
  }

  public AtiopathogeneseCluster getAtiopathogenese() {
     return this.atiopathogenese ;
  }

  public void setDatumZeitpunktDerGenesungValue(TemporalAccessor datumZeitpunktDerGenesungValue) {
     this.datumZeitpunktDerGenesungValue = datumZeitpunktDerGenesungValue;
  }

  public TemporalAccessor getDatumZeitpunktDerGenesungValue() {
     return this.datumZeitpunktDerGenesungValue ;
  }

  public void setStatus(List<Cluster> status) {
     this.status = status;
  }

  public List<Cluster> getStatus() {
     return this.status ;
  }

  public void setDiagnoseerlauterungValue(String diagnoseerlauterungValue) {
     this.diagnoseerlauterungValue = diagnoseerlauterungValue;
  }

  public String getDiagnoseerlauterungValue() {
     return this.diagnoseerlauterungValue ;
  }

  public void setLetztesDokumentationsdatumValue(TemporalAccessor letztesDokumentationsdatumValue) {
     this.letztesDokumentationsdatumValue = letztesDokumentationsdatumValue;
  }

  public TemporalAccessor getLetztesDokumentationsdatumValue() {
     return this.letztesDokumentationsdatumValue ;
  }

  public void setErweiterung(List<Cluster> erweiterung) {
     this.erweiterung = erweiterung;
  }

  public List<Cluster> getErweiterung() {
     return this.erweiterung ;
  }

  public void setSubject(PartyProxy subject) {
     this.subject = subject;
  }

  public PartyProxy getSubject() {
     return this.subject ;
  }

  public void setLanguage(Language language) {
     this.language = language;
  }

  public Language getLanguage() {
     return this.language ;
  }

  public void setDiagnostischeSicherheit(
      ProblemDiagnoseDiagnostischeSicherheitChoice diagnostischeSicherheit) {
     this.diagnostischeSicherheit = diagnostischeSicherheit;
  }

  public ProblemDiagnoseDiagnostischeSicherheitChoice getDiagnostischeSicherheit() {
     return this.diagnostischeSicherheit ;
  }

  public void setSchweregrad(ProblemDiagnoseSchweregradChoice schweregrad) {
     this.schweregrad = schweregrad;
  }

  public ProblemDiagnoseSchweregradChoice getSchweregrad() {
     return this.schweregrad ;
  }
}