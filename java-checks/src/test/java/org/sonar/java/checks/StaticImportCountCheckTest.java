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

public class StaticImportCountCheckTest {

  private static final String TEST_FILES_DIR = "src/test/files/checks/StaticImportCountCheck/";

  @Test
  public void static_imports_below_threshold_are_compliant() {
    JavaCheckVerifier.newVerifier()
      .onFile(TEST_FILES_DIR + "CompliantImports.java")
      .withCheck(new StaticImportCountCheck())
      .verifyNoIssues();
  }

  @Test
  public void cu_with_just_static_imports() {
    JavaCheckVerifier.newVerifier()
      .onFile(TEST_FILES_DIR + "StaticImportCountCheck.java")
      .withCheck(new StaticImportCountCheck())
      .verifyIssues();
  }

  @Test
  public void cu_with_normal_and_static_imports() {
    JavaCheckVerifier.newVerifier()
      .onFile(TEST_FILES_DIR + "MixedStandardAndStaticImports.java")
      .withCheck(new StaticImportCountCheck())
      .verifyIssues();
  }

  @Test
  public void cu_with_custom_threshold_compliant() {
    StaticImportCountCheck check = new StaticImportCountCheck();
    check.setThreshold(5);
    JavaCheckVerifier.newVerifier()
      .onFile(TEST_FILES_DIR + "MixedStandardAndStaticImportsCompliant.java")
      .withCheck(check)
      .verifyNoIssues();
  }

  @Test
  public void cu_with_custom_threshold_noncompliant() {
    StaticImportCountCheck check = new StaticImportCountCheck();
    check.setThreshold(3);
    JavaCheckVerifier.newVerifier()
      .onFile(TEST_FILES_DIR + "MixedStandardAndStaticImportsCustomThreshold.java")
      .withCheck(check)
      .verifyIssues();
  }

}
