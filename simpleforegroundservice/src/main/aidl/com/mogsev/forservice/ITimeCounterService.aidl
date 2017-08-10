// ITimeCounter.aidl
package com.mogsev.forservice;

import com.mogsev.forservice.aidlmodel.Punch;

interface ITimeCounterService {

    long getMillis();
    Punch getPunch();

}
