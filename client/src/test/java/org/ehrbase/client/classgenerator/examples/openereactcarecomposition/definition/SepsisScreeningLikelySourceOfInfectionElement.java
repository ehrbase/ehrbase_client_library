package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import org.ehrbase.client.annotations.Choice;
import org.ehrbase.client.annotations.Entity;
import org.ehrbase.client.annotations.Path;

@Entity
public class SepsisScreeningLikelySourceOfInfectionElement {
  @Path("/value")
  @Choice
  private SepsisScreeningValueChoice value;

  public void setValue(SepsisScreeningValueChoice value) {
     this.value = value;
  }

  public SepsisScreeningValueChoice getValue() {
     return this.value ;
  }
}