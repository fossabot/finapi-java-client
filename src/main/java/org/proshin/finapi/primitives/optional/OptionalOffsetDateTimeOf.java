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
package org.proshin.finapi.primitives.optional;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.function.Supplier;
import org.json.JSONObject;
import org.proshin.finapi.primitives.OffsetDateTimeOf;

public final class OptionalOffsetDateTimeOf implements Supplier<Optional<OffsetDateTime>> {

    private final JSONObject origin;
    private final String name;
    private final String pattern;

    public OptionalOffsetDateTimeOf(final JSONObject origin, final String name) {
        this(origin, name, "yyyy-MM-dd HH:mm:ss.SSS");
    }

    public OptionalOffsetDateTimeOf(final JSONObject origin, final String name, final String pattern) {
        this.origin = origin;
        this.name = name;
        this.pattern = pattern;
    }

    @Override
    public Optional<OffsetDateTime> get() {
        return this.origin.isNull(this.name)
            ? Optional.empty()
            : Optional.of(new OffsetDateTimeOf(this.origin.getString(this.name), this.pattern).get());
    }
}
