/*
Copyright 2015 Google Inc. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.google.security.zynamics.reil.algorithms.mono.valuetracking.transformers;

import com.google.security.zynamics.reil.ReilInstruction;
import com.google.security.zynamics.reil.algorithms.mono.valuetracking.ValueTrackerElement;
import com.google.security.zynamics.reil.algorithms.mono.valuetracking.elements.Addition;
import com.google.security.zynamics.reil.algorithms.mono.valuetracking.elements.IElementGenerator;
import com.google.security.zynamics.reil.algorithms.mono.valuetracking.elements.IValueElement;

/**
 * During range tracking, this transformer transforms the global state whenever an ADD instruction
 * is processed.
 */
public final class AddTransformer extends BaseTransformer {
  private static final AddGenerator ADD_GENERATOR = new AddGenerator();

  public static ValueTrackerElement transform(final ReilInstruction instruction,
      final ValueTrackerElement incomingState) {
    return transform(instruction, incomingState, ADD_GENERATOR);
  }

  private static class AddGenerator implements IElementGenerator {
    @Override
    public IValueElement generate(final IValueElement lhs, final IValueElement rhs) {
      // TODO:
      // X + 0
      // 0 + X

      return new Addition(lhs, rhs).getSimplified();
    }
  }
}
