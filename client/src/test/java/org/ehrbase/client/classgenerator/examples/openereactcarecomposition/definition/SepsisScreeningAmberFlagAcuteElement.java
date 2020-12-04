package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class SepsisScreeningAmberFlagAcuteElement {
  /**
   * open_eREACT-Care/Assessment/Sepsis/Sepsis screening/Any event/Amber flag (acute)
   */
  @Path("/value|defining_code")
  private AmberFlagAcuteDefiningCode value;

  public void setValue(AmberFlagAcuteDefiningCode value) {
     this.value = value;
  }

  public AmberFlagAcuteDefiningCode getValue() {
     return this.value ;
  }
}
