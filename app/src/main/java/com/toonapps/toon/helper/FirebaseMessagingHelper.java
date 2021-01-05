/*
 * Copyright (c) 2020
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements
 * See the NOTICE file distributed with this work for additional information regarding copyright ownership
 * The ASF licenses this file to you under the Apache License, Version 2.0 (the  "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
 * express or implied.  See the License for the specific language governing permissions and limitations
 * under the License.
 */

package com.toonapps.toon.helper;

import com.google.firebase.iid.FirebaseInstanceId;

import timber.log.Timber;

public class FirebaseMessagingHelper {

    public static void getFCMInstanceId() {

        Timber.d("Requesting Firebase Messaging token");
        FirebaseInstanceId.getInstance().getInstanceId()
            .addOnCompleteListener(task -> {
                if (task.isSuccessful() && task.getResult() != null) {
                    AppSettings.getInstance().
                            setFirebaseInstanceId(
                                    task.getResult().getToken()
                            );
                    Timber.d("Got a Firebase Messaging token: %s", task.getResult().getToken());
                } else {
                    //TODO let user know registration failed
                }
            });
    }
}