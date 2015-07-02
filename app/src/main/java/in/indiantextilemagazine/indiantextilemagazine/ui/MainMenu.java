package in.indiantextilemagazine.indiantextilemagazine.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;

import org.json.JSONArray;
import org.json.JSONException;

import in.indiantextilemagazine.indiantextilemagazine.R;
import in.indiantextilemagazine.indiantextilemagazine.ServerInteraction.RetrieveJSON;
import in.indiantextilemagazine.indiantextilemagazine.Utilities.CommonData;
import in.indiantextilemagazine.indiantextilemagazine.Utilities.ConnectionDetector;
import in.indiantextilemagazine.indiantextilemagazine.Utilities.TextileIndiaPreferences;

public class MainMenu extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,RetrieveJSON.MyCallbackInterface {

    private static final int START_INDEX = 1;
    private static final int END_INDEX = 20;
    String url = CommonData.SITE_URL;

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    public static final String TAG = "Main Menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        /*update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                .commit();
        */

        //TODO never hardcode
        if(position == 17 || position == 18){
            // set the URL to which the user is sent to when he clicks on the image
            if(position == 17){
                url = "http://www.motorindiaonline.in/catalogs-brochures/";
            }
            if(position == 18){
                url = "http://www.motorindiaonline.in/subscribe/";
            }

            Log.i(TAG,"you will be shown a image");
            // Create a new fragment and replace container
            Fragment fragment = new ImageFragment();
            Bundle args = new Bundle();
            args.putInt(TextileIndiaPreferences.CATEGORY_NUMBER, position);
            fragment.setArguments(args);

            // Insert the fragment by replacing any existing fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commit();
            return;
        }
        String link = "http://motorindiaonline.in/mobapp/?s_i="+Integer.toString(START_INDEX)+"&e_i="+Integer.toString(END_INDEX)+"&cat_i=";

        // Start fetching content for the list
        switch (position){
            case 0:
                link = link +"featured";
                break;
            case 1:
                link = link +"trucks";
                break;
            case 2:
                link = link +"buses";
                break;
            case 3:
                link = link +"construction-equipment";
                break;
            case 4:
                link = link +"applications";
                break;
            case 5:
                link = link +"component";
                break;
            case 6:
                link = link +"aftermarket";
                break;
            case 7:
                link = link +"tyres";
                break;
            case 8:
                link = link +"lubes-fuels";
                break;
            case 9:
                link = link +"batteries";
                break;
            case 10:
                link = link +"technology";
                break;
            case 11:
                link = link +"logistics";
                break;
            case 12:
                link = link +"dealer-corner";
                break;
            case 13:
                link = link +"bazaar-talk";
                break;
            case 14:
                link = link +"csr-initiative-2";
                break;
            case 15:
                link = link +"events";
                break;
            case 16:
                link = link +"impact-feature";
                break;
        }

        // Start a thread to go fetch article data
        new RetrieveJSON(this).execute(link);

        // Create a new fragment and replace container
        Fragment fragment = new LoadingFragment();
        Bundle args = new Bundle();
        args.putInt(TextileIndiaPreferences.CATEGORY_NUMBER, position);
        fragment.setArguments(args);

        // Insert the fragment by replacing any existing fragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.container, fragment)
                .commit();

    }

    public void onSectionAttached(int number) {
        //TODO Add categories here
        switch (number) {
            case 0:
                mTitle = getString(R.string.title_section1);
                break;
            case 1:
                mTitle = getString(R.string.title_section2);
                break;
            case 2:
                mTitle = getString(R.string.title_section3);
                break;
            case 3:
                mTitle = getString(R.string.title_section4);
                break;
            case 4:
                mTitle = getString(R.string.title_section5);
                break;
            case 5:
                mTitle = getString(R.string.title_section6);
                break;
            case 6:
                mTitle = getString(R.string.title_section7);
                break;
            case 7:
                mTitle = getString(R.string.title_section8);
                break;
            case 8:
                mTitle = getString(R.string.title_section9);
                break;
            case 9:
                mTitle = getString(R.string.title_section10);
                break;
            case 10:
                mTitle = getString(R.string.title_section11);
                break;
            case 11:
                mTitle = getString(R.string.title_section12);
                break;
            case 12:
                mTitle = getString(R.string.title_section12);
                break;
            case 13:
                mTitle = getString(R.string.title_section14);
                break;
            case 14:
                mTitle = getString(R.string.title_section15);
                break;
            case 15:
                mTitle = getString(R.string.title_section16);
                break;
            case 16:
                mTitle = getString(R.string.title_section17);
                break;
            case 17:
                mTitle = getString(R.string.title_section18);
                break;
            case 18:
                mTitle = getString(R.string.title_section19);
                break;
        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        // Deprecated - TODO loads of deprecated API being used
        // I may have to move to using toolbar, rather than a actionbar
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.main_menu, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            //TODO launch intent to settings page
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void launchBrowser(View view) {
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

    // This function is called if somehow a request for the article list fails. this fixes the bug
    // of the user stuck at loading.
    public void requestAgain(){
        //Check if this is due to internet problem
        Log.i(TAG, "checking connection");
        ConnectionDetector connectionDetector = new ConnectionDetector(getApplicationContext());

        // Check if Internet present
        if (!connectionDetector.isConnectedInternet()) {
            //Stop progressBar
            SplashScreen.mProgressBar.setVisibility(ProgressBar.GONE);

            // Internet Connection is not present, I make a special alert to get the user to access internet
            // Here as I need the dialog to be a inner class to send the intent and call finish

            new AlertDialog.Builder(this)
                    .setTitle("Internet Connection Error")
                    .setMessage("Please connect to working Internet connection")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Log.i(TAG, "ok has been clicked");
                            Intent i = new Intent(Settings.ACTION_WIFI_SETTINGS);
                            startActivity(i);
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            Log.i(TAG, "Cancel has been clicked");
                            finish();
                        }
                    })
                    .setIcon(R.drawable.alert)
                    .setCancelable(false)
                    .show();
            return;
        }
        Log.i(TAG, "finished checking");

        int position = 0;
        Log.i(TAG,"Requesting AGAIN for article list");
        if(mTitle == getString(R.string.title_section1)){
            position = 0;
        } else if(mTitle == getString(R.string.title_section2)){
            position = 1;
        } else if(mTitle == getString(R.string.title_section3)){
            position = 2;
        } else if(mTitle == getString(R.string.title_section4)){
            position = 3;
        } else if(mTitle == getString(R.string.title_section5)){
            position = 4;
        } else if(mTitle == getString(R.string.title_section6)){
            position = 5;
        } else if(mTitle == getString(R.string.title_section7)){
            position = 6;
        } else if(mTitle == getString(R.string.title_section8)){
            position = 7;
        } else if(mTitle == getString(R.string.title_section9)){
            position = 8;
        } else if(mTitle == getString(R.string.title_section10)){
            position = 9;
        } else if(mTitle == getString(R.string.title_section11)){
            position = 10;
        } else if(mTitle == getString(R.string.title_section12)){
            position = 11;
        } else if(mTitle == getString(R.string.title_section13)){
            position = 12;
        } else if(mTitle == getString(R.string.title_section14)){
            position = 13;
        } else if(mTitle == getString(R.string.title_section15)){
            position = 14;
        } else if(mTitle == getString(R.string.title_section16)){
            position = 15;
        } else if(mTitle == getString(R.string.title_section17)){
            position = 16;
        }

        String link = "http://motorindiaonline.in/mobapp/?s_i="+Integer.toString(START_INDEX)+"&e_i="+Integer.toString(END_INDEX)+"&cat_i=";

        switch (position){
            case 0:
                link = link +"featured";
                break;
            case 1:
                link = link +"trucks";
                break;
            case 2:
                link = link +"buses";
                break;
            case 3:
                link = link +"construction-equipment";
                break;
            case 4:
                link = link +"applications";
                break;
            case 5:
                link = link +"component";
                break;
            case 6:
                link = link +"aftermarket";
                break;
            case 7:
                link = link +"tyres";
                break;
            case 8:
                link = link +"lubes-fuels";
                break;
            case 9:
                link = link +"batteries";
                break;
            case 10:
                link = link +"technology";
                break;
            case 11:
                link = link +"logistics";
                break;
            case 12:
                link = link +"dealer-corner";
                break;
            case 13:
                link = link +"bazaar-talk";
                break;
            case 14:
                link = link +"csr-initiative-2";
                break;
            case 15:
                link = link +"events";
                break;
            case 16:
                link = link +"impact-feature";
                break;
        }

        // Start a thread again to go fetch article data
        new RetrieveJSON(this).execute(link);
    }

    @Override
    public void onRequestCompleted(JSONArray result) {
        if(result == null){
            Log.i(TAG,"Thread returned NULL");
            // This could mean that the user is waiting for the article to load, so we need to request again
            requestAgain();
            return;
        }
        try {
            String url = result.getJSONObject(result.length()-1).getString(TextileIndiaPreferences.URL_JSON);
            Log.i(TAG,"We have received result of the request to the URL = "+url);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Now depending on what the current screen is using mTitle we can know that
        // we need to set the data into the article fragment
        Log.i(TAG,"Replacing the existing fragment ");
        // Create a new fragment and replace container
        Fragment fragment = new ArticleFragment();
        Bundle args = new Bundle();
        String articleData = result.toString();
        args.putString(TextileIndiaPreferences.ARTICLE_DATA, articleData);
        fragment.setArguments(args);
        try {
            // Insert the fragment by replacing any existing fragment AND allowStateLoss -
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, fragment)
                    .commitAllowingStateLoss();

        } catch (IllegalStateException e) {
            Log.i(TAG,"Its cool, no activity, we don't need to perform the transaction");
        }

    }


    /**
     * A placeholder fragment containing a simple view. used in the naviagtion drawer
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main_menu, container, false);

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainMenu) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

    /**
     *  The fragment to hold Articles
     */
    public static class ArticleFragment extends Fragment {

        private static final String TAG = "ArticleFragmentRecycler";
        // Used to represent a fixed set of constants
        private enum LayoutManagerType {
            GRID_LAYOUT_MANAGER,
            LINEAR_LAYOUT_MANAGER
        }


        protected LayoutManagerType mCurrentLayoutManagerType;
        protected RadioButton mLinearLayoutRadioButton;
        protected RadioButton mGridLayoutRadioButton;
        protected RecyclerView mRecyclerView;
        protected CustomAdapter mAdapter;
        protected RecyclerView.LayoutManager mLayoutManager;
        protected String[] mDataset;

        private static int SPAN_COUNT = 1;

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ArticleFragment newInstance(int sectionNumber) {
            ArticleFragment fragment = new ArticleFragment();
            Bundle args = new Bundle();
            args.putInt(TextileIndiaPreferences.CATEGORY_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public ArticleFragment() {
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            // To ensure that the articles are such that a single column if portrait
            int rotation = getResources().getConfiguration().orientation;
            if(rotation == 1){
                // now let there be 1 column for portrait
                SPAN_COUNT = 1;
            }
            else if(rotation == 2){
                // 2 columns for landscape
                SPAN_COUNT = 2;
            }

            View rootView = inflater.inflate(R.layout.fragment_articlelist, container, false);
            Log.i(TAG, "onCreate view was called with args" + getArguments().getString(TextileIndiaPreferences.ARTICLE_DATA));

            // store the articleList data
            mDataset = new String[1];
            mDataset[0] = getArguments().getString(TextileIndiaPreferences.ARTICLE_DATA);

            // setTag - It's basically a way for views to have memories.
            rootView.setTag(TAG);

            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);

            // LinearLayoutManager is used here, this will layout the elements in a similar fashion
            // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
            // elements are laid out.
            mLayoutManager = new LinearLayoutManager(getActivity());

            mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;

            if (savedInstanceState != null) {
                // Restore saved layout manager type.
                mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                        .getSerializable(TextileIndiaPreferences.KEY_LAYOUT_MANAGER);
            }
            setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

            mAdapter = new CustomAdapter(mDataset);
            // Set CustomAdapter as the adapter for RecyclerView.
            mRecyclerView.setAdapter(mAdapter);

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainMenu) activity).onSectionAttached(
                    getArguments().getInt(TextileIndiaPreferences.CATEGORY_NUMBER));
        }

        /**
         * Set RecyclerView's LayoutManager to the one given.
         *
         * @param layoutManagerType Type of layout manager to switch to.
         */
        public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
            int scrollPosition = 0;

            // If a layout manager has already been set, get current scroll position.
            if (mRecyclerView.getLayoutManager() != null) {
                scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                        .findFirstCompletelyVisibleItemPosition();
            }

            switch (layoutManagerType) {
                case GRID_LAYOUT_MANAGER:
                    mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                    mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                    break;
                case LINEAR_LAYOUT_MANAGER:
                    mLayoutManager = new LinearLayoutManager(getActivity());
                    mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                    break;
                default:
                    mLayoutManager = new LinearLayoutManager(getActivity());
                    mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
            }

            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.scrollToPosition(scrollPosition);
        }

        @Override
        public void onSaveInstanceState(Bundle savedInstanceState) {
            // Save currently selected layout manager.
            savedInstanceState.putSerializable(TextileIndiaPreferences.KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
            super.onSaveInstanceState(savedInstanceState);
        }
    }

    /**
     *  The fragment to hold a single Image which on clicking directs you to a appropriate website
     */
    public static class ImageFragment extends Fragment {

        private static final String TAG = "ImageFragment";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static ImageFragment newInstance(int sectionNumber) {
            ImageFragment fragment = new ImageFragment();
            Bundle args = new Bundle();
            args.putInt(TextileIndiaPreferences.CATEGORY_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public ImageFragment() {
            Log.i(TAG," Image Fragment Constructor called");
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_image, container, false);

            Integer position = getArguments().getInt(TextileIndiaPreferences.CATEGORY_NUMBER);
            ImageView img = (ImageView)rootView.findViewById(R.id.imageView);

            // TODO never hardcode
            if(position == 17){
                Log.i(TAG,"Setting catalog picture");
                img.setImageResource(R.drawable.cata);
            }
            else if(position == 18){
                Log.i(TAG,"Setting subscribe photo");
                img.setImageResource(R.drawable.subscribe);
            }

            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainMenu) activity).onSectionAttached(
                    getArguments().getInt(TextileIndiaPreferences.CATEGORY_NUMBER));
        }
    }

    /**
     *  The fragment to show a loading symbol
     */
    public static class LoadingFragment extends Fragment {

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static LoadingFragment newInstance(int sectionNumber) {
            LoadingFragment fragment = new LoadingFragment();
            Bundle args = new Bundle();
            args.putInt(TextileIndiaPreferences.CATEGORY_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public LoadingFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_loading, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainMenu) activity).onSectionAttached(
                    getArguments().getInt(TextileIndiaPreferences.CATEGORY_NUMBER));
        }
    }

}
