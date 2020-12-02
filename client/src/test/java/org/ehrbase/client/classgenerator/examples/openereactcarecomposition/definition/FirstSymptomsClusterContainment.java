package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datatypes.CodePhrase;
import java.lang.String;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalAmount;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class FirstSymptomsClusterContainment extends Containment {
  public SelectAqlField<FirstSymptomsCluster> FIRST_SYMPTOMS_CLUSTER = new AqlFieldImp<FirstSymptomsCluster>(FirstSymptomsCluster.class, "", "FirstSymptomsCluster", FirstSymptomsCluster.class, this);

  public SelectAqlField<CodePhrase> SYMPTOM_SIGN_NAME_DEFINING_CODE = new AqlFieldImp<CodePhrase>(FirstSymptomsCluster.class, "/items[at0001.1]/value|defining_code", "symptomSignNameDefiningCode", CodePhrase.class, this);

  public ListSelectAqlField<Cluster> STRUCTURED_BODY_SITE = new ListAqlFieldImp<Cluster>(FirstSymptomsCluster.class, "/items[at0147]", "structuredBodySite", Cluster.class, this);

  public SelectAqlField<TemporalAccessor> DATE_OF_ONSET_OF_FIRST_SYMPTOMS_VALUE = new AqlFieldImp<TemporalAccessor>(FirstSymptomsCluster.class, "/items[at0152]/value|value", "dateOfOnsetOfFirstSymptomsValue", TemporalAccessor.class, this);

  public SelectAqlField<TemporalAmount> DURATION_VALUE = new AqlFieldImp<TemporalAmount>(FirstSymptomsCluster.class, "/items[at0028]/value|value", "durationValue", TemporalAmount.class, this);

  public SelectAqlField<TrendDefiningCode> TREND_DEFINING_CODE = new AqlFieldImp<TrendDefiningCode>(FirstSymptomsCluster.class, "/items[at0180]/value|defining_code", "trendDefiningCode", TrendDefiningCode.class, this);

  public SelectAqlField<String> IMPACT_VALUE = new AqlFieldImp<String>(FirstSymptomsCluster.class, "/items[at0155]/value|value", "impactValue", String.class, this);

  public ListSelectAqlField<Cluster> SPECIFIC_DETAILS = new ListAqlFieldImp<Cluster>(FirstSymptomsCluster.class, "/items[at0153]", "specificDetails", Cluster.class, this);

  public ListSelectAqlField<Cluster> PREVIOUS_EPISODES = new ListAqlFieldImp<Cluster>(FirstSymptomsCluster.class, "/items[at0146]", "previousEpisodes", Cluster.class, this);

  public ListSelectAqlField<Cluster> ASSOCIATED_SYMPTOM_SIGN = new ListAqlFieldImp<Cluster>(FirstSymptomsCluster.class, "/items[at0063]", "associatedSymptomSign", Cluster.class, this);

  public SelectAqlField<PresenceDefiningCode> PRESENCE_DEFINING_CODE = new AqlFieldImp<PresenceDefiningCode>(FirstSymptomsCluster.class, "/items[at0.1]/value|defining_code", "presenceDefiningCode", PresenceDefiningCode.class, this);

  private FirstSymptomsClusterContainment() {
    super("openEHR-EHR-CLUSTER.symptom_sign-cvid.v0");
  }

  public static FirstSymptomsClusterContainment getInstance() {
    return new FirstSymptomsClusterContainment();
  }
}