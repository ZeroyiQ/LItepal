package top.zeroyiq.litepal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "Database";

    @OnClick(R.id.btn_create_database)
    void create() {
        Connector.getDatabase();
        Toast.makeText(this, "You clicked", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btn_add)
    void add() {
        Book book = new Book();
        book.setName("ZeroyiQ's Book");
        book.setAuthor("ZeroyiQ");
        book.setPages(180);
        book.setPrice(18);
        book.setPress("Unknow");
        book.save();
    }

    @OnClick(R.id.btn_update)
    void update() {

//        方法一：当再次调用 Save() 方法的时候，Litepal 会检测，发现当前 book 已存储，会直接更新当前数据。
        Book book = new Book();
        book.setName("ZeroyiQ's Book2");
        book.setAuthor("ZeroyiQ");
        book.setPages(180);
        book.setPrice(18);
        book.setPress("Unknow");
        book.save();
        book.setPrice(15);
        book.save();

//        通过 updateAll() 指定一个约束条件，修改所有满足约束条件的数据。
//        Book book = new Book();
//        book.setPrice(15);
//        book.setPress("Anchor");
//        book.updateAll("name= ? and author = ?","ZeroyiQ's Book2","ZeroyiQ");

//        通过 setToDefault() 方法，可以使特定列，更新成默认值
//        Book book = new Book();
//        book.setToDefault("pages");
//        book.updateAll();

    }

    @OnClick(R.id.btn_delete_data)
    void delete() {
        DataSupport.deleteAll(Book.class, "price > ?", "16");
    }


    @OnClick(R.id.btn_query_data)
    void query() {
//        List<Book> books = DataSupport.findAll(Book.class);
        List<Book> books = DataSupport.select("name","author").find(Book.class);

        for (Book book : books) {

            Log.d(TAG, "Book ID is " + book.getId());
            Log.d(TAG, "Book name is " + book.getName());
            Log.d(TAG, "Book author is " + book.getAuthor());
            Log.d(TAG, "Book pages is " + book.getPages());
            Log.d(TAG, "Book price is " + book.getPrice());
            Log.d(TAG, "Book press is " + book.getPress());

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }
}
