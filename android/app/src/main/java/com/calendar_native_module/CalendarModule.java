package com.calendar_native_module;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.modules.core.DeviceEventManagerModule;

import java.util.Map;
import java.util.HashMap;

import android.util.Log;

import javax.annotation.Nullable;

public class CalendarModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext context;

    CalendarModule(ReactApplicationContext context) {
        super(context);
        this.context = context;
    }

    @Override
    public String getName() {
        return "CalendarModule";
    }

    @ReactMethod
    public void createCalendarEvent(String name, String location, Callback callback) {
        try {
          Log.d("CalendarModule", "Create event called with name: " + name + " and location: " + location);
          Double eventId = Math.random();
          callback.invoke(eventId);

        } catch(Exception e) {
          e.printStackTrace();
        }
    }

    @ReactMethod
    public void createCalendarEventPromise(String name, String location, Promise promise) {
        Log.d("CalendarModule", "Create event called with name: " + name + " and location: " + location);

        try {
            Double eventId = Math.random();
            promise.resolve(eventId);
        } catch(Exception e) {
            promise.reject("Error occurred ", e);
        }
    }
    
    @ReactMethod
    public void eventReminder() {
      WritableMap params = Arguments.createMap();
      params.putString("eventProperty", "some value");

      this.sendEvent(getReactApplicationContext(), "EventReminder", params);
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();
        constants.put("DEFAULT_EVENT_NAME", "New Event");
        return constants;
    }

    private void sendEvent(ReactContext reactContext, String eventName, WritableMap params) {
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter.class)
                .emit(eventName, params);
    }
}

