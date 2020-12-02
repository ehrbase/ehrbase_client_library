package org.ehrbase.client.classgenerator.examples.openereactcarecomposition.definition;

import com.nedap.archie.rm.datastructures.Cluster;
import com.nedap.archie.rm.datatypes.CodePhrase;
import org.ehrbase.client.aql.containment.Containment;
import org.ehrbase.client.aql.field.AqlFieldImp;
import org.ehrbase.client.aql.field.ListAqlFieldImp;
import org.ehrbase.client.aql.field.ListSelectAqlField;
import org.ehrbase.client.aql.field.SelectAqlField;

public class KeyCovidSymptomClusterContainment extends Containment {
  public SelectAqlField<KeyCovidSymptomCluster> KEY_COVID_SYMPTOM_CLUSTER = new AqlFieldImp<KeyCovidSymptomCluster>(KeyCovidSymptomCluster.class, "", "KeyCovidSymptomCluster", KeyCovidSymptomCluster.class, this);

  public SelectAqlField<CodePhrase> SYMPTOM_SIGN_NAME_DEFINING_CODE = new AqlFieldImp<CodePhrase>(KeyCovidSymptomCluster.class, "/items[at0001.1]/value|defining_code", "symptomSignNameDefiningCode", CodePhrase.class, this);

  public ListSelectAqlField<Cluster> STRUCTURED_BODY_SITE = new ListAqlFieldImp<Cluster>(KeyCovidSymptomCluster.class, "/items[at0147]", "structuredBodySite", Cluster.class, this);

  public ListSelectAqlField<Cluster> SPECIFIC_DETAILS = new ListAqlFieldImp<Cluster>(KeyCovidSymptomCluster.class, "/items[at0153]", "specificDetails", Cluster.class, this);

  public ListSelectAqlField<Cluster> PREVIOUS_EPISODES = new ListAqlFieldImp<Cluster>(KeyCovidSymptomCluster.class, "/items[at0146]", "previousEpisodes", Cluster.class, this);

  public ListSelectAqlField<Cluster> ASSOCIATED_SYMPTOM_SIGN = new ListAqlFieldImp<Cluster>(KeyCovidSymptomCluster.class, "/items[at0063]", "associatedSymptomSign", Cluster.class, this);

  public SelectAqlField<PresenceDefiningCode> PRESENCE_DEFINING_CODE = new AqlFieldImp<PresenceDefiningCode>(KeyCovidSymptomCluster.class, "/items[at0.1]/value|defining_code", "presenceDefiningCode", PresenceDefiningCode.class, this);

  private KeyCovidSymptomClusterContainment() {
    super("openEHR-EHR-CLUSTER.symptom_sign-cvid.v0");
  }

  public static KeyCovidSymptomClusterContainment getInstance() {
    return new KeyCovidSymptomClusterContainment();
  }
}