/**
 * Copyright (C) FuseSource, Inc.
 * http://fusesource.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fusesource.process.fabric.child;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.fusesource.process.manager.InstallOptions;

/**
 * Describes the requirements of a child process
 */
public class ProcessRequirements {
    private String id;
    private String kind;
    private String url;
    private List<DeploymentInfo> deployments = new ArrayList<DeploymentInfo>();
    private List<String> profiles = new ArrayList<String>();

    public ProcessRequirements(String id) {
        this.id = id;
    }

    public String toString() {
        return "Process(" + id  + " " + kind + " " + url + " profiles: " + profiles + ")";
    }

    public List<DeploymentInfo> getDeployments() {
        return deployments;
    }

    public String getId() {
        return id;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void addProfile(String profile) {
        profiles.add(profile);
    }

    public List<String> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<String> profiles) {
        this.profiles = profiles;
    }

    /**
     * Creates the installation options for the process given the requirements
     */
    public InstallOptions createInstallOptions() throws MalformedURLException {
        InstallOptions.InstallOptionsBuilder builder = InstallOptions.builder().name(id);
        if (url != null) {
            builder = builder.url(url);
        }
        if (kind != null) {
            if (kind.indexOf(':') < 0) {
                kind = "profile:" + kind + ".json";
            }
            builder = builder.controllerUrl(kind);
        }
        return builder.build();
    }
}
