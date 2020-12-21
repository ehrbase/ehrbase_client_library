package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.archetyped.FeederAudit;
import javax.annotation.processing.Generated;
import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;
import org.ehrbase.client.classgenerator.interfaces.LocatableEntity;

@Entity
@Generated(
    value = "org.ehrbase.client.classgenerator.ClassGenerator",
    date = "2020-12-10T13:06:11.387501400+01:00",
    comments = "https://github.com/ehrbase/openEHR_SDK Version: null")
public class DenwisTemperatureIndicatorElement implements LocatableEntity {
  /** Path: open_eREACT-Care/Assessment/DENWIS/Point in time/feeder_audit */
  @Path("/feeder_audit")
  private FeederAudit feederAudit;

  /** Path: open_eREACT-Care/Assessment/DENWIS/Point in time/value */
  @Path("/value")
  @Choice
  private DenwisValueChoice value;

  public void setFeederAudit(FeederAudit feederAudit) {
    this.feederAudit = feederAudit;
  }

  public FeederAudit getFeederAudit() {
    return this.feederAudit;
  }

  public void setValue(DenwisValueChoice value) {
    this.value = value;
  }

  public DenwisValueChoice getValue() {
    return this.value;
  }
}
