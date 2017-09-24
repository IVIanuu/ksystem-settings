/*
 * Copyright 2017 Manuel Wrage
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

package com.ivianuu.rxsystemsettings;

import android.provider.Settings;
import android.support.annotation.NonNull;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * Checks if a specific setting exists
 */
final class SettingsValidator {

    private static final HashMap<String, Boolean> CACHED = new HashMap<>();

    /**
     * Returns whether the setting exists or not
     */
    static boolean doesExist(@NonNull String name) {
        if (CACHED.containsKey(name)) {
            return CACHED.get(name);
        }

        boolean exists = false;

        // global
        exists = doesGlobalSettingExists(name);

        // secure
        if (!exists) {
            exists = doesSecureSettingExists(name);
        }

        // system
        if (!exists) {
            exists = doesSystemSettingExists(name);
        }


        CACHED.put(name, exists);
        return exists;
    }

    private static boolean doesGlobalSettingExists(String name) {
        return checkFieldValues(Settings.Global.class.getDeclaredFields(), name);
    }

    private static boolean doesSecureSettingExists(String name) {
        return checkFieldValues(Settings.Secure.class.getDeclaredFields(), name);
    }

    private static boolean doesSystemSettingExists(String name) {
        return checkFieldValues(Settings.System.class.getDeclaredFields(), name);
    }

    private static boolean checkFieldValues(Field[] fields, String value) {
        for (Field field : fields) {
            try {
                String fieldValue = (String) field.get(null);
                if (fieldValue.equals(value)) {
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return false;
    }
}
