package com.islamsaeed.news.API;


import com.islamsaeed.news.API.Model.NewsResponce;
import com.islamsaeed.news.API.Model.SourcesResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebServices {
    /* هنا هاحط الميثود اللي ها represent  بيها ال API  اللي انا هاعملها
     * يعني هاحط فيه كل ال API  اللي انا عايز اعملها Call
     * وكل API  بحطلها نوع ال Response  بتاعها
     * ونوع ال Request  وال URL  بتاعه */


    @GET ("sources")
    Call<SourcesResponse> getNewsSources(@Query("apiKey") String apiKey,
                                         @Query("language") String language);
    /*لو انا محتاج اثبت حاجة في كل requests
    هاروح احط ال parameters بتاعتي فالميثود
    يبقي الميثود دي هاترجعلي مصادر الأخبار بناءا علي ال api key  وال language*/


    /*هايعرف منين انه هاينادي ال API  دي
     * محتاج ال URL  ونوع ال Request اللي هو GRT
     * وال GET  بياخد parameters اللي هو ال URL بتاع ال API دي
     *
     *
     * بعد كده هاروح أ build object من class  ال Retrofit  بتاعي وال class  ده هايبقي شغال فال singleton
     * يعني طول ما الأبليكيشن شغال انا عندي object  واحد بس من Class ده */





    /*هاعمل ال API  التانية*/

    @GET("everything")
    Call<NewsResponce>getNews(@Query("apiKey") String apiKey,
                              @Query("language") String language,
                              @Query("sources") String sources);
}
