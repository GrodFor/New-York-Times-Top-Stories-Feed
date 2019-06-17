package sitsko.vlad.epamandroidproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import sitsko.vlad.epamandroidproject.model.ArticleModel;
import sitsko.vlad.epamandroidproject.model.MultimediaModel;

public class DetailPageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailpage);

        Intent intent = getIntent();
        final ArticleModel articleModel = (ArticleModel) intent.getSerializableExtra("article");

        TextView textView = findViewById(R.id.title_textView);
        TextView textView1 = findViewById(R.id.abstract_textView);
        TextView textView2 = findViewById(R.id.author_textView);
        TextView textView3 = findViewById(R.id.date_textView);
        Button openBrowserBtn = findViewById(R.id.open_browser_button);
        ImageView imageView = findViewById(R.id.big_imageView);

        final int multimediaLength = articleModel.getMultimedia().length;

        if (multimediaLength > 0) {
            final MultimediaModel multimediaModel = articleModel.getMultimedia()[multimediaLength-1];
            Picasso.get().load(multimediaModel.getUrl())
                    .placeholder(R.drawable.nytlogo)
                    .error(R.drawable.ic_placeholder_error)
                    .into(imageView);
        } else {
            imageView.setVisibility(View.GONE);
        }

        textView.setText(articleModel.getTitle());
        textView1.setText(articleModel.getParagraph());
        textView2.setText(articleModel.getByline());
        textView3.setText(articleModel.getPublished_date());

        openBrowserBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(articleModel.getShort_url())));
            }
        });


    }
}
