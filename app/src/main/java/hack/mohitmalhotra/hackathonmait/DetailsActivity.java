package hack.mohitmalhotra.hackathonmait;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

import hack.mohitmalhotra.hackathonmait.adapters.FragmentAdapter;
import hack.mohitmalhotra.hackathonmait.fragments.BorrowFragment;
import hack.mohitmalhotra.hackathonmait.fragments.DonateFragment;
import hack.mohitmalhotra.hackathonmait.utils.DecodeBitmapTask;
import hack.mohitmalhotra.hackathonmait.widgets.CustomViewPager;


public class DetailsActivity extends AppCompatActivity implements DecodeBitmapTask.Listener {

    static final String BUNDLE_IMAGE_ID = "BUNDLE_IMAGE_ID";

    private ImageView imageView;
    private DecodeBitmapTask decodeBitmapTask;

    private CustomViewPager pager;
    private FragmentAdapter fragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        final int smallResId = getIntent().getIntExtra(BUNDLE_IMAGE_ID, -1);
        if (smallResId == -1) {
            finish();
            return;
        }

        imageView = (ImageView)findViewById(R.id.image);
        imageView.setImageResource(smallResId);

        pager = findViewById(R.id.formPager);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailsActivity.super.onBackPressed();
            }
        });

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            loadFullSizeBitmap(smallResId);
        } else {
            getWindow().getSharedElementEnterTransition().addListener(new Transition.TransitionListener() {

                private boolean isClosing = false;

                @Override public void onTransitionPause(Transition transition) {}
                @Override public void onTransitionResume(Transition transition) {}
                @Override public void onTransitionCancel(Transition transition) {}

                @Override public void onTransitionStart(Transition transition) {
                    if (isClosing) {
                        addCardCorners();
                    }
                }

                @Override public void onTransitionEnd(Transition transition) {
                    if (!isClosing) {
                        isClosing = true;

                        removeCardCorners();
                        loadFullSizeBitmap(smallResId);
                    }
                }
            });
        }

        initFragmentsWithPager();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (isFinishing() && decodeBitmapTask != null) {
            decodeBitmapTask.cancel(true);
        }
    }

    private void addCardCorners() {
        final CardView cardView = (CardView) findViewById(R.id.card);
        cardView.setRadius(25f);
    }

    private void removeCardCorners() {
        final CardView cardView = (CardView)findViewById(R.id.card);
        ObjectAnimator.ofFloat(cardView, "radius", 0f).setDuration(50).start();
    }

    private void loadFullSizeBitmap(int smallResId) {
        int bigResId;
        switch (smallResId) {
            case R.drawable.p1: bigResId = R.drawable.p1_big; break;
            case R.drawable.p2: bigResId = R.drawable.p2_big; break;
            case R.drawable.p3: bigResId = R.drawable.p3_big; break;
            case R.drawable.p4: bigResId = R.drawable.p4_big; break;
            case R.drawable.p5: bigResId = R.drawable.p5_big; break;
            default: bigResId = R.drawable.p1_big;
        }

        final DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getRealMetrics(metrics);

        final int w = metrics.widthPixels;
        final int h = metrics.heightPixels;

        decodeBitmapTask = new DecodeBitmapTask(getResources(), bigResId, w, h, this);
        decodeBitmapTask.execute();
    }

    @Override
    public void onPostExecuted(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }

    private void initFragmentsWithPager() {
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new DonateFragment());
        fragments.add(new BorrowFragment());

        fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments);

        pager.setAdapter(fragmentAdapter);
        pager.setPagingEnabled(false);
    }


    public void nextPage(){
        if(pager.getChildCount() > pager.getCurrentItem()) {
            pager.setCurrentItem(pager.getCurrentItem() + 1);
        }
    }

    public void previousPage() {
        if (pager.getCurrentItem() > 0) {
            pager.setCurrentItem(pager.getCurrentItem() - 1);
        }
    }

    @Override
    public void onBackPressed() {
        if(pager.getCurrentItem() > 0){
            this.previousPage();
        } else {
            super.onBackPressed();
        }
    }
}
