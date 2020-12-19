package com.squarecheck.calendar.interactor;

import com.squarecheck.base.util.UtilProvider;
import com.squarecheck.calendar.contract.CalendarContract;
import com.squarecheck.calendar.model.CalendarModel;
import com.squarecheck.calendar.retrofit.CalendarService;
import com.squarecheck.shared.callback.RequestCallback;
import com.squarecheck.shared.callback.RetrofitCallback;
import com.squarecheck.shared.model.APIResponseCollection;
import com.squarecheck.shared.retrofit.ServiceGenerator;
import com.squarecheck.shared.util.TokenUtil;
import java.util.List;

import retrofit2.Call;

public class CalendarInteractor implements CalendarContract.Interactor {
    private static final String TAG = com.squarecheck.calendar.interactor.CalendarInteractor.class.getSimpleName();
    private final CalendarService service;

    public CalendarInteractor() {
        String token = ((TokenUtil) UtilProvider.getUtil(TokenUtil.class)).getSessionData().getToken();
        service = ServiceGenerator.createService(CalendarService.class, token);
    }

    @Override
    public void requestCalendar(RequestCallback<List<CalendarModel>> callback) {
        Call<APIResponseCollection<List<CalendarModel>>> call = service.getCalendars();
        call.enqueue(new RetrofitCallback<>(callback, TAG, "requestCalendar"));
    }
}
