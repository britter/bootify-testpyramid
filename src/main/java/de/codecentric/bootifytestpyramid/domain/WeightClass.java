/*
 * Copyright 2017 Benedikt Ritter
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
package de.codecentric.bootifytestpyramid.domain;

public enum WeightClass {

    UP_TO_100,
    FROM_100_TO_500,
    ABOVE_500;

    private static final Weight HUNDRED = new Weight(100);

    private static final Weight FIVE_HUNDRED = new Weight(500);

    public static WeightClass valueOf(final Weight weight) {
        if (weight.compareTo(HUNDRED) <= 0) {
            return UP_TO_100;
        } else if (weight.compareTo(HUNDRED) > 0 && weight.compareTo(FIVE_HUNDRED) <= 0) {
            return FROM_100_TO_500;
        } else {
            return ABOVE_500;
        }
    }
}
