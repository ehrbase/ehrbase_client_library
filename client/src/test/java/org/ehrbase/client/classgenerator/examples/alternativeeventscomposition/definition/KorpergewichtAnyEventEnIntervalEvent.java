package org.ehrbase.client.classgenerator.examples.alternativeeventscomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.IntervalEventEntity;
import org.ehrbase.client.classgenerator.shareddefinition.MathFunction;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:10.104492600+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
@OptionFor("INTERVAL_EVENT")
public class KorpergewichtAnyEventEnIntervalEvent
    implements IntervalEventEntity, KorpergewichtAnyEventEnChoice {
  /**
   * Path: Bericht/Körpergewicht/*Any event(en)/Gewicht Description: Das Gewicht eines Individuums.
   */
  @Path("/data[at0001]/items[at0004]/value|magnitude")
  private Double gewichtMagnitude;

  /**
   * Path: Bericht/Körpergewicht/*Any event(en)/Gewicht Description: Das Gewicht eines Individuums.
   */
  @Path("/data[at0001]/items[at0004]/value|units")
  private String gewichtUnits;

  /**
   * Path: Bericht/Körpergewicht/*Any event(en)/*Comment(en) Description: *Additional narrative
   * about the measurement of Body weight, not captured in other fields.(en)
   */
  @Path("/data[at0001]/items[at0024]/value|value")
  private String commentEnValue;

  /**
   * Path: Bericht/Körpergewicht/*Any event(en)/*State of dress(en) Description: *Description of the
   * state of dress of the person at the time of weighing.(en)
   */
  @Path("/state[at0008]/items[at0009]/value|defining_code")
  private StateOfDressEnDefiningCode stateOfDressEnDefiningCode;

  /**
   * Path: Bericht/Körpergewicht/*Any event(en)/*Confounding factors(en) Description: *Record any
   * issues or factors that may impact on the measurement of body weight eg timing in menstrual
   * cycle, timing of recent bowel motion or noting of amputation.(en)
   */
  @Path("/state[at0008]/items[at0025]")
  private List<KorpergewichtConfoundingFactorsEnElement> confoundingFactorsEn;

  /** Path: Bericht/Körpergewicht/*Any event(en)/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: Bericht/Körpergewicht/*Any event(en)/time */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /** Path: Bericht/Körpergewicht/*Any event(en)/width */
  @Path("/width|value")
  private TemporalAmount widthValue;

  /** Path: Bericht/Körpergewicht/*Any event(en)/math_function */
  @Path("/math_function|defining_code")
  private MathFunction mathFunctionDefiningCode;

  /** Path: Bericht/Körpergewicht/*Any event(en)/sample_count */
  @Path("/sample_count")
  private Long sampleCount;

  public void setGewichtMagnitude(Double gewichtMagnitude) {
    this.gewichtMagnitude = gewichtMagnitude;
  }

  public Double getGewichtMagnitude() {
    return this.gewichtMagnitude;
  }

  public void setGewichtUnits(String gewichtUnits) {
    this.gewichtUnits = gewichtUnits;
  }

  public String getGewichtUnits() {
    return this.gewichtUnits;
  }

  public void setCommentEnValue(String commentEnValue) {
    this.commentEnValue = commentEnValue;
  }

  public String getCommentEnValue() {
    return this.commentEnValue;
  }

  public void setStateOfDressEnDefiningCode(StateOfDressEnDefiningCode stateOfDressEnDefiningCode) {
    this.stateOfDressEnDefiningCode = stateOfDressEnDefiningCode;
  }

  public StateOfDressEnDefiningCode getStateOfDressEnDefiningCode() {
    return this.stateOfDressEnDefiningCode;
  }

  public void setConfoundingFactorsEn(
      List<KorpergewichtConfoundingFactorsEnElement> confoundingFactorsEn) {
    this.confoundingFactorsEn = confoundingFactorsEn;
  }

  public List<KorpergewichtConfoundingFactorsEnElement> getConfoundingFactorsEn() {
    return this.confoundingFactorsEn;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
    this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
    return this.timeValue;
  }

  public void setWidthValue(TemporalAmount widthValue) {
    this.widthValue = widthValue;
  }

  public TemporalAmount getWidthValue() {
    return this.widthValue;
  }

  public void setMathFunctionDefiningCode(MathFunction mathFunctionDefiningCode) {
    this.mathFunctionDefiningCode = mathFunctionDefiningCode;
  }

  public MathFunction getMathFunctionDefiningCode() {
    return this.mathFunctionDefiningCode;
  }

  public void setSampleCount(Long sampleCount) {
    this.sampleCount = sampleCount;
  }

  public Long getSampleCount() {
    return this.sampleCount;
  }
}
