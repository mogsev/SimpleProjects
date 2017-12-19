package bookieblowout.com.testrestapi.network;

import bookieblowout.com.testrestapi.network.model.MarkList;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Eugene Sikaylo on 12/19/2017.
 * email: mogsev@gmail.com
 */

public interface ApiService {

    @GET("cars/get-mark-list")
    Observable<MarkList> getMarkList();

}
