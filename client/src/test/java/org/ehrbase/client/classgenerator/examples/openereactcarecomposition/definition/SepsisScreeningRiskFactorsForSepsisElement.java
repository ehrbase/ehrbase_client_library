package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class SepsisScreeningRiskFactorsForSepsisElement {
  @Path("/value|defining_code")
  private RiskFactorsForSepsisDefiningCode value;

  public void setValue(RiskFactorsForSepsisDefiningCode value) {
     this.value = value;
  }

  public RiskFactorsForSepsisDefiningCode getValue() {
     return this.value ;
  }
}