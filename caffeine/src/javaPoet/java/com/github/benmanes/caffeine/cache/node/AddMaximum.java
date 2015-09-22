/*
 * Copyright 2015 Ben Manes. All Rights Reserved.
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
package com.github.benmanes.caffeine.cache.node;

import javax.lang.model.element.Modifier;

import com.github.benmanes.caffeine.cache.Feature;
import com.squareup.javapoet.MethodSpec;

/**
 * Adds the maximum metadata to the node.
 *
 * @author ben.manes@gmail.com (Ben Manes)
 */
public final class AddMaximum extends NodeRule {

  @Override
  protected boolean applies() {
    return Feature.usesMaximum(context.generateFeatures);
  }

  @Override
  protected void execute() {
    context.nodeSubtype.addField(int.class, "moveCount", Modifier.PRIVATE);
    context.nodeSubtype.addMethod(MethodSpec.methodBuilder("getMoveCount")
        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
        .returns(int.class)
        .addStatement("return moveCount")
        .build());
    context.nodeSubtype.addMethod(MethodSpec.methodBuilder("setMoveCount")
        .addModifiers(Modifier.PUBLIC, Modifier.FINAL)
        .addParameter(int.class, "moveCount")
        .addStatement("this.moveCount = moveCount")
        .build());
  }
}
