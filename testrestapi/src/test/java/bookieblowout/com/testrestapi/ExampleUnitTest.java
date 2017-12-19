package bookieblowout.com.testrestapi;

import org.junit.Ignore;
import org.junit.Test;

import bookieblowout.com.testrestapi.network.Api;
import bookieblowout.com.testrestapi.network.model.CarMark;
import bookieblowout.com.testrestapi.network.model.MarkList;
import io.reactivex.Observable;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    @Ignore
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void getMarkList_successful() throws Exception {
        Api api = new Api();

        Observable<MarkList> observable = api.getApiService().getMarkList();
        observable.doOnSubscribe(disposable -> System.out.println("START"))
                .doOnTerminate(() -> System.out.println("END"))
                .subscribe(markList -> System.out.println(markList)
                        , throwable -> System.err.println(throwable));


    }

}