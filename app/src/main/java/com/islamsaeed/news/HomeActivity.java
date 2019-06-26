package com.islamsaeed.news;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.islamsaeed.news.API.APIManager;
import com.islamsaeed.news.API.Model.ArticlesItem;
import com.islamsaeed.news.API.Model.NewsResponce;
import com.islamsaeed.news.API.Model.SourcesItem;
import com.islamsaeed.news.API.Model.SourcesResponse;
import com.islamsaeed.news.Base.BaseActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends BaseActivity {

    TabLayout tabLayout;
    RecyclerView recyclerView;
    List<ArticlesItem> articles ;

    NewsAdapter newsAdapter ;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabLayout);
        recyclerView = findViewById(R.id.recyclerView);
        initRecyclerView();
        // بعدكده هاروح اعمل set  لل Adapter  في ال loadNewsBySourceId


        /*المفروض اني اول حاجة هاعملها اني ها load  مصادر الأخبار*/
        loadNewsSource();


//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
    }






    public void initRecyclerView (){
        newsAdapter = new NewsAdapter(null);
        recyclerView.setAdapter(newsAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));

    }





    public void loadNewsSource(){
        // call Api
    showProgressBar(R.string.loading);
    // get api
        APIManager.getApis()
                .getNewsSources(Constants.API_KEY,Constants.LANGUAGE)
                .enqueue(new Callback<SourcesResponse>() {
                    @Override
                    public void onResponse(Call<SourcesResponse> call,
                                           Response<SourcesResponse> response) {

                        /*المفروض هنا جايلي ال Response  بتاعت الأخبار
                        اللي هاخدها احطها فال tabLayout*/

                        hideProgressDialog();
                        setTabLayoutWithNewsSources(response.body().getSources());
                    }

                    @Override
                    public void onFailure(Call<SourcesResponse> call, Throwable t) {

                        hideProgressDialog();
                        showMessage(R.string.error , t.getLocalizedMessage(),R.string.ok);
                    }
                });
    }



    /* والمفروض هانادي الميثود دي أما اجي ادوس علي Tab  وهايطلب مني ال id  بتاعه ويجيبهولي */
    public void loadNewsBySourceId(String  sourceId){
        // show progressbar
        showProgressBar(R.string.loading);
        // call Api
        APIManager.getApis()
                .getNews(Constants.API_KEY,Constants.LANGUAGE ,sourceId +"" )
                .enqueue(new Callback<NewsResponce>() {
                    @Override
                    public void onResponse(Call<NewsResponce> call, Response<NewsResponce> response) {

                        hideProgressDialog();
                        /*المفروض هايجيبلي List<News>*/
                        articles = response.body().getArticles();

                        // بعد كده هاروح اعمل ال RecyclerView  اللي بيعرض الخبر

                        /* والمفروض هانادي الميثود دي أما اجي ادوس علي Tab  وهايطلب مني ال id  بتاعه ويجيبهولي
                         * ودي هاعملها ازاي ؟
                          * هاروح احط عال Tab  Listener
                         لميثود بتاعت      setTabLayoutWithNewsSources
                         */

                        newsAdapter.changeData(articles);
                    }

                    @Override
                    public void onFailure(Call<NewsResponce> call, Throwable t) {

                        hideProgressDialog();
                        showMessage(R.string.error , t.getLocalizedMessage(),R.string.ok);

                    }
                });
    }

    private void setTabLayoutWithNewsSources(final List<SourcesItem> sources) {
      // معايا list المفروض اني احطها فال tab
        for (int i =0 ; i<sources.size();i++)
        {
            // هاخد اسم كل source وابعته لل tab
            TabLayout.Tab tab = tabLayout.newTab(); //   ولازم اعمل الخطوة دي الأول عشان اشتغل مع ال tabLayout , بقول لل tabLayout  يعملي تاب جديدة
            tab.setText(sources.get(i).getName());
            tab.setTag(sources.get(i));
            /*هاتفيدني اما اعوز اجيب parameters من ال tag  ده
            * يبقي كل tab  محطوط معاه ال tag  بتاعه وال object  بتاع ال source*/
            tabLayout.addTab(tab);
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // انا عايز هنا انادي عال API

                /*هنا عشان اجيب ال id هاجيب الأول ال tab position
                * ومكانه ده هو هو مكانه اللي فال List  اللي جاية فال Parameter
                * فلازم اروح اجيب ال id  من الليست دي*/

                // الحل الأول
//                int tabPos = tab.getPosition();
//                loadNewsBySourceId(sources.get(tabPos).getId());

                // الحل الثاني تكملة اللي فوق بتاع ال tag

                SourcesItem item = ((SourcesItem) tab.getTag());
                loadNewsBySourceId(item.getId());

                // هاروح اعمل ال adapter
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


}
