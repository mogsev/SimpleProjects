package com.mogsev.simpleprojects.viewmodel;

import android.databinding.ObservableField;

/**
 * Created by Eugene Sikaylo on 13.07.2017.
 */

public class CatTwoViewModel {

    public final ObservableField<String> name = new ObservableField<String>();

    public final ObservableField<String> description = new ObservableField<String>();

    public final ObservableField<String> image = new ObservableField<String>();

}
