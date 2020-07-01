/*
 *
 *  *  Copyright (c) 2020  Stefan Spiska (Vitasystems GmbH) and Hannover Medical School
 *  *  This file is part of Project EHRbase
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *  http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *
 */

package org.ehrbase.client.classgenerator.examples.shareddefinition;

import org.ehrbase.client.classgenerator.EnumValueSet;

public enum TransitionDefiningcode implements EnumValueSet {
    TIMEOUT("time out", "time out", "openehr", "549"),

    ACTIVESTEP("active step", "active step", "openehr", "543"),

    ABORT("abort", "abort", "openehr", "547"),

    FINISH("finish", "finish", "openehr", "548"),

    SUSPEND("suspend", "suspend", "openehr", "544"),

    POSTPONE("postpone", "postpone", "openehr", "537"),

    DO("do", "do", "openehr", "541"),

    RESUME("resume", "resume", "openehr", "546"),

    SCHEDULE("schedule", "schedule", "openehr", "539"),

    PLANSTEP("plan step", "plan step", "openehr", "536"),

    CANCEL("cancel", "cancel", "openehr", "166"),

    POSTPONEDSTEP("postponed step", "postponed step", "openehr", "542"),

    SCHEDULEDSTEP("scheduled step", "scheduled step", "openehr", "534"),

    NOTIFYABORTED("notify aborted", "notify aborted", "openehr", "550"),

    NOTIFYCOMPLETED("notify completed", "notify completed", "openehr", "551"),

    NOTIFYCANCELLED("notify cancelled", "notify cancelled", "openehr", "552"),

    RESTORE("restore", "restore", "openehr", "538"),

    START("start", "start", "openehr", "540"),

    SUSPENDEDSTEP("suspended step", "suspended step", "openehr", "545"),

    INITIATE("initiate", "initiate", "openehr", "535");

    private String value;

    private String description;

    private String terminologyId;

    private String code;

    TransitionDefiningcode(String value, String description, String terminologyId, String code) {
        this.value = value;
        this.description = description;
        this.terminologyId = terminologyId;
        this.code = code;
    }

    public String getValue() {
        return this.value;
    }

    public String getDescription() {
        return this.description;
    }

    public String getTerminologyId() {
        return this.terminologyId;
    }

    public String getCode() {
        return this.code;
    }
}
