/*
 * Copyright DataStax, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.datastax.driver.core.exceptions;

import com.datastax.driver.core.EndPoint;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/** Indicates a syntactically correct but invalid query. */
public class InvalidQueryException extends QueryValidationException
    implements CoordinatorException {

  private static final long serialVersionUID = 0;

  private final EndPoint endPoint;

  public InvalidQueryException(String msg) {
    this(null, msg);
  }

  public InvalidQueryException(EndPoint endPoint, String msg) {
    super(msg);
    this.endPoint = endPoint;
  }

  public InvalidQueryException(String msg, Throwable cause) {
    this(null, msg, cause);
  }

  public InvalidQueryException(EndPoint endPoint, String msg, Throwable cause) {
    super(msg, cause);
    this.endPoint = endPoint;
  }

  @Override
  public DriverException copy() {
    return new InvalidQueryException(getEndPoint(), getMessage(), this);
  }

  @Override
  public EndPoint getEndPoint() {
    return endPoint;
  }

  @Override
  @Deprecated
  public InetSocketAddress getAddress() {
    return (endPoint == null) ? null : endPoint.resolve();
  }

  @Override
  @Deprecated
  public InetAddress getHost() {
    return (endPoint == null) ? null : endPoint.resolve().getAddress();
  }
}
