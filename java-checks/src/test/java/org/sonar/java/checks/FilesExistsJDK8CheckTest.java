/*
 * SonarQube Java
 * Copyright (C) 2012-2020 SonarSource SA
 * mailto:info AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.java.checks;

import org.junit.jupiter.api.Test;
import org.sonar.java.checks.verifier.JavaCheckVerifier;

public class FilesExistsJDK8CheckTest {

  @Test
  public void java8() {
    JavaCheckVerifier.newVerifier()
      .onFile("src/test/files/checks/FilesExistsJDK8Check.java")
      .withCheck(new FilesExistsJDK8Check())
      .withJavaVersion(8)
      .verifyIssues();
  }

  @Test
  public void java7() {
    JavaCheckVerifier.newVerifier()
      .onFile("src/test/files/checks/FilesExistsJDK8Check_java7.java")
      .withCheck(new FilesExistsJDK8Check())
      .withJavaVersion(7)
      .verifyNoIssues();
  }

  @Test
  public void unknown_version() {
    JavaCheckVerifier.newVerifier()
      .onFile("src/test/files/checks/FilesExistsJDK8Check_no_version.java")
      .withCheck(new FilesExistsJDK8Check())
      .verifyNoIssues();
  }
}
