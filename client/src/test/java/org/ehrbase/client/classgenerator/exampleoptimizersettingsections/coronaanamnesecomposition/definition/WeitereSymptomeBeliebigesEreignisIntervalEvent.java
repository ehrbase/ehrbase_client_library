package org.ehrbase.client.classgenerator.exampleoptimizersettingsections.coronaanamnesecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import java.lang.Long;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import java.util.List;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.OptionFor;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.IntervalEventEntity;
import org.ehrbase.client.classgenerator.shareddefinition.MathFunction;

@Entity
@OptionFor("INTERVAL_EVENT")
public class WeitereSymptomeBeliebigesEreignisIntervalEvent implements IntervalEventEntity, WeitereSymptomeBeliebigesEreignisChoice {
  /**
   * Bericht/Symptome/Weitere Symptome/Beliebiges Ereignis/Spezifisches Symptom/Anzeichen
   */
  @Path("/data[at0003]/items[at0022]")
  private List<WeitereSymptomeSpezifischesSymptomAnzeichenCluster> spezifischesSymptomAnzeichen;

  /**
   * Bericht/Symptome/Weitere Symptome/Beliebiges Ereignis/feeder_audit
   */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /**
   * Bericht/Symptome/Weitere Symptome/Beliebiges Ereignis/time
   */
  @Path("/time|value")
  private TemporalAccessor timeValue;

  /**
   * Bericht/Symptome/Weitere Symptome/Beliebiges Ereignis/width
   */
  @Path("/width|value")
  private TemporalAmount widthValue;

  /**
   * Bericht/Symptome/Weitere Symptome/Beliebiges Ereignis/math_function
   */
  @Path("/math_function|defining_code")
  private MathFunction mathFunctionDefiningCode;

  /**
   * Bericht/Symptome/Weitere Symptome/Beliebiges Ereignis/sample_count
   */
  @Path("/sample_count")
  private Long sampleCount;

  public void setSpezifischesSymptomAnzeichen(
      List<WeitereSymptomeSpezifischesSymptomAnzeichenCluster> spezifischesSymptomAnzeichen) {
     this.spezifischesSymptomAnzeichen = spezifischesSymptomAnzeichen;
  }

  public List<WeitereSymptomeSpezifischesSymptomAnzeichenCluster> getSpezifischesSymptomAnzeichen(
      ) {
     return this.spezifischesSymptomAnzeichen ;
  }

  public void setFeederAudit(FeederAudit feederAudit) {
     this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
     return this.feederAudit ;
  }

  public void setTimeValue(TemporalAccessor timeValue) {
     this.timeValue = timeValue;
  }

  public TemporalAccessor getTimeValue() {
     return this.timeValue ;
  }

  public void setWidthValue(TemporalAmount widthValue) {
     this.widthValue = widthValue;
  }

  public TemporalAmount getWidthValue() {
     return this.widthValue ;
  }

  public void setMathFunctionDefiningCode(MathFunction mathFunctionDefiningCode) {
     this.mathFunctionDefiningCode = mathFunctionDefiningCode;
  }

  public MathFunction getMathFunctionDefiningCode() {
     return this.mathFunctionDefiningCode ;
  }

  public void setSampleCount(Long sampleCount) {
     this.sampleCount = sampleCount;
  }

  public Long getSampleCount() {
     return this.sampleCount ;
  }
}