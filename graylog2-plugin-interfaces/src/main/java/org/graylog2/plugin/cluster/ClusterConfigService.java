/**
 * The MIT License
 * Copyright (c) 2012 Graylog, Inc.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.graylog2.plugin.cluster;

import java.util.Optional;
import java.util.Set;

/**
 * Service to save and retrieve cluster configuration beans.
 */
public interface ClusterConfigService {
    /**
     * Retrieve Java class of a certain type from the cluster configuration.
     *
     * @param type The {@link Class} of the Java configuration bean to retrieve.
     * @param <T>  The type of the Java configuration bean.
     * @return An instance of the requested type wrapped in {@link Optional}.
     */
    <T> Optional<T> get(Class<T> type);

    /**
     * Retrieve Java class of a certain type from the cluster configuration or return the default value
     * of the configuration class using a static method with signature {@code public static <T> T defaultConfig()}
     * in case it wasn't found.
     *
     * @param type The {@link Class} of the Java configuration bean to retrieve.
     * @param <T>  The type of the Java configuration bean.
     * @return An instance of the requested type wrapped in {@link Optional}.
     */
    <T> Optional<T> getOrDefault(Class<T> type);

    /**
     * Return the default config instance of a cluster config class using a static method with signature
     * {@code public static <T> T defaultConfig()}.
     *
     * @param type The {@link Class} of the Java configuration bean to retrieve.
     * @param <T>  The type of the Java configuration bean.
     * @return An instance of the requested type wrapped in {@link Optional}.
     */
    <T> Optional<T> getDefault(Class<T> type);

    /**
     * Write a configuration bean to the cluster configuration.
     *
     * @param payload The object to write to the cluster configuration. Must be serializable by Jackson!
     * @param <T>     The type of the Java configuration bean.
     */
    <T> void write(T payload);

    /**
     * Remove a configuration bean from the cluster configuration.
     *
     * @param type The {@link Class} of the Java configuration bean to remove.
     * @return The number of removed entries from the cluster configuration.
     */
    <T> int remove(Class<T> type);

    /**
     * List all classes of configuration beans in the database.
     *
     * @return The list of Java classes being used in the database.
     */
    Set<Class<?>> list();
}
