package com.islamsaeed.news.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {

    /*اول حاجة هانعمل object  من ال Retrofit وهايكون singleton */

   private static   Retrofit retrofit ;

   public static  Retrofit getInstance(){
       // Your API key is: 81919828d95c400eaa1c66e38c4b3110

       if (retrofit==null)
       {
           /* to build this object go to documentation for retrofit*/

           retrofit = new Retrofit.Builder()
                   .baseUrl("https://newsapi.org/v2/")
                   .addConverterFactory( GsonConverterFactory.create())
                   .build();
       }
       return retrofit;
   }

   /* بعد كده هاستخم ال class  اللي اسمه WebServices  */

    public static WebServices getApis (){
        /*هاتنادي getInstance . create(WebServices.class)*/
        /* في اللحظة دي هايحصل build في ال compile time ال Annotation processor
         هايشيل ال Annotation  ويبني classes كاملة بتاعت ال APIs دي   */

        return getInstance().create(WebServices.class);

        /*لو عايز اعمل API  في أبليكيشن هاجي عند ال ApiManager  اغير ال baseUrl
        * وبعد كده هاغير ال Apis اللي انا بناديها اللي هي في interface webServices
         * هاخد ال package  كلها copy
         *
         *
         * الخطوة اللي فضلالي اني انادي عال API */
    }
}
