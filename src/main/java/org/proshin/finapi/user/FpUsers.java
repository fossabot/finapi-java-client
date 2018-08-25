/*
 * Copyright 2018 Roman Proshin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.proshin.finapi.user;

import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import org.proshin.finapi.accesstoken.AccessToken;
import org.proshin.finapi.endpoint.Endpoint;
import org.proshin.finapi.user.in.CreateParameters;

public final class FpUsers implements Users {

    private final Endpoint endpoint;
    private final AccessToken token;

    public FpUsers(final Endpoint endpoint, final AccessToken token) {
        this.endpoint = endpoint;
        this.token = token;
    }

    @Override
    public boolean verified(final String userId) {
        return new JSONObject(
            this.endpoint.get("/api/v1/users/verificationStatus", this.token)
        ).getBoolean("isUserVerified");
    }

    @Override
    public User create(final CreateParameters parameters) {
        return new FpUser(
            new JSONObject(
                this.endpoint.post(
                    "/api/v1/users",
                    this.token,
                    new StringEntity(parameters.asJson(), ContentType.APPLICATION_JSON),
                    201
                )
            )
        );
    }

    @Override
    public String requestPasswordChange(final String userId) {
        throw new UnsupportedOperationException("This method is not implemented yet");
    }

    @Override
    public void executePasswordChange(final String userId, final String password, final String token) {
        throw new UnsupportedOperationException("This method is not implemented yet");
    }

    @Override
    public void verify(final String userId) {
        throw new UnsupportedOperationException("This method is not implemented yet");
    }

    @Override
    public void deleteUnverified(final String userId) {
        throw new UnsupportedOperationException("This method is not implemented yet");
    }
}
