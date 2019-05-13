package com.example.fah.FAHScreen.Other;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fah.R;

public class SecurityActivity extends AppCompatActivity {
    private String titleB;
    private String titleC;
    private String titleD;
    private String titleE;
    private String titleF;
    private String titleG;
    private String titleH;
    private String titleI;
    private String contentA;
    private String contentB;
    private String contentC;
    private String contentD;
    private String contentE;
    private String contentF;
    private String contentG;
    private String contentH;
    private String contentI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_security);

        initContentData();
        getContentTextView();
    }
    private void initContentData(){
        contentA = "Dữ liệu của bạn sẽ được lưu và xử lý tổng thể hay từng phần ở Việt Nam. Nếu bạn truy cập một App BaBaQ từ một địa điểm nằm ngoài Việt Nam," +
                " việc sử dụng site đó được hiểu là bạn đồng ý chuyển dữ liệu của mình ra ngoài phạm vi nước đó và gửi tới Việt Nam." +
                " Các site của chúng tôi chứa các liên kết tới các site khác mà chúng tôi không kiểm soát. Navigos Group, Ltd, công ty sở hữu và điều hành App BaBaQ," +
                " không chịu trách nhiệm về các chính sách bảo mật hoặc việc thực hiện chúng của các site mà bạn kết nối tới từ App BaBaQ." +
                " Chúng tôi khuyến khích bạn xem xét các chính sách bảo mật thông tin của các site khác đó để có thể hiểu cách thức họ thu thập, sử dụng và chia sẻ thông tin của bạn." +
                " Chính sách Bảo mật này chỉ áp dụng cho các thông tin mà chúng tôi thu thập trên App BaBaQ và không áp dụng cho thông tin mà chúng tôi thu thập được theo cách khác.";

        titleB = "Các thông tin có thể nhận dạng cá nhân nào được thu thập từ bạn";
        contentB = "App BaBaQ thu thập thông tin theo một số cách từ các mục khác nhau trên App của mình.\n" +
                "Một số thông tin cá nhân được tập hợp lại khi bạn đăng ký. Trong khi đăng ký, chúng tôi hỏi tên và địa chỉ email của bạn." +
                " Hệ thống cũng có thể hỏi địa chỉ đường phố, thành phố, bang/tỉnh, mã vùng/bưu điện, quốc gia, số điện thoại, thông tin thanh toán và địa chỉ máy (URL) của App của bạn," +
                " mặc dù chỉ những trường được đánh dấu sao * trên phần đăng ký mới là thông tin bắt buộc." +
                " App BaBaQ cũng thu thập hoặc có thể thu thập thông tin nhân khẩu học không chỉ từ riêng bạn như tuổi tác, ưu tiên, giới tính, các mối quan tâm và sở thích." +
                " Đôi khi chúng tôi thu thập hoặc có thể thu thập một kết hợp của hai kiểu thông tin. Ngay khi bạn đăng ký, bạn không còn vô danh với App BaBaQ nữa," +
                " bạn có tên truy cập và có thể khai thác đầy đủ các sản phẩm/dịch vụ của App BaBaQ.\n" +
                "Ngoài thông tin đăng ký, đôi khi chúng tôi có thể hỏi bạn thông tin cá nhân bao gồm (nhưng không hạn chế) khi bạn đặt đăng quảng cáo tuyển dụng hoặc khai thác" +
                " các tính năng khác của App BaBaQ. Nếu bạn liên lạc với chúng tôi, chúng tôi có thể giữ một bản ghi nhớ về sự liên lạc này.\n" +
                "Mỗi trang trong phạm vi App BaBaQ đều có đường dẫn tới Chính sách Bảo mật này.\n";

        titleC = "Thông tin của bạn được sử dụng như thế nào";
        contentC = "Bằng việc cung cấp thông tin của mình, bạn đồng ý để BaBaQ App, các công ty liên kết, đơn vị trực thuộc và các thành viên trực thuộc Navigos Group có thể sử dụng thông tin của bạn, dù đó là thông tin cá nhân, nhân khẩu học, tập hợp hay kỹ thuật, đều nhằm mục đích điều hành và cải tiến App BaBaQ, tăng cường tiện ích cho người sử dụng hoặc giới thiệu và phân phối các sản phẩm và dịch vụ của chúng tôi.\n" +
                "Chúng tôi cũng có thể dùng thông tin thu thập được để thông báo cho bạn về những sản phẩm và dịch vụ do App BaBaQ hay các công ty đối tác cung cấp, hoặc để xin ý kiến của bạn về các sản phẩm và dịch vụ hiện tại hay những sản phẩm và dịch vụ tiềm năng mới.\n" +
                "Chúng tôi cũng có thể dùng thông tin liên lạc của bạn để gửi cho bạn email hoặc các thông báo khác về những cập nhật tại App tuyển dụng của BaBaQ. Nội dung và tần suất của những thông báo này sẽ thay đổi tuỳ thuộc vào thông tin mà chúng tôi có về bạn. Ngoài ra, vào lúc đăng ký, bạn có quyền lựa chọn nhận những thông tin, thông báo và các chương trình khuyến mãi bao gồm nhưng không hạn chế các bản tin miễn phí từ App BaBaQ liên quan đến những chủ đề mà bạn có thể đặc biệt quan tâm.\n" +
                "Chúng tôi có một khu vực để bạn có thể liên lạc với chúng tôi. Bất kỳ phản hồi nào bạn gửi đến cho chúng tôi sẽ trở thành tài sản của chúng tôi và chúng tôi có thể dùng phản hồi đó (chẳng hạn các câu chuyện thành công) cho các mục đích tiếp thị, hoặc liên hệ với bạn để có thêm thông tin.\n";

        titleD = "Ai đang thu thập thông tin của bạn";
        contentD = "Khi được hỏi về các thông tin cá nhân trên App tuyển dụng của BaBaQ, có nghĩa là bạn đang chia sẻ thông tin đó với riêng App BaBaQ," +
                " trừ phi có thông báo cụ thể khác. Tuy nhiên, một số hoạt động do đặc trưng của chúng, sẽ dẫn đến việc thông tin cá nhân của bạn được tiết lộ cho" +
                " những người sử dụng khác của App BaBaQ biết. Ví dụ, khi bạn điền thông tin cá nhân lên bản đăng quảng cáo tuyển dụng," +
                " thông tin này nói chung sẽ được gộp trong công việc của bạn, trừ phi có thông báo cụ thể khác.";

        titleE = "Thông tin của bạn có thể chia sẻ với ai";
        contentE = "Chúng tôi không tiết lộ cho bên thứ ba thông tin cá nhân của bạn, cũng như thông tin cá nhân và nhân khẩu học kết hợp, hoặc thông tin về việc sử dụng App BaBaQ của bạn (chẳng hạn các khu vực bạn ghé thăm, hay các dịch vụ mà bạn truy cập), trừ năm mục sau đây.\n" +
                "\t1.\tChúng tôi có thể để lộ thông tin như vậy cho các nhóm thứ ba nếu bạn đồng ý. Ví dụ, nếu bạn cho biết bạn muốn nhận thông tin về các sản phẩm và dịch vụ của bên thứ ba khi đăng ký một tài khoản trên App BaBaQ, chúng tôi có thể cung cấp thông tin liên hệ của bạn cho bên thứ ba đó, ví dụ người sử dụng lao động, các nhà tuyển dụng, người thu thập dữ liệu, nhân viên thị trường hoặc những người khác với mục đích gửi email cho bạn hay liên lạc với bạn theo cách khác. Chúng tôi có thể dùng dữ liệu đã có về bạn (như các mối quan tâm hay sở thích mà bạn đã trình bày) để xác định xem liệu bạn có thể quan tâm đến các sản phẩm hay dịch vụ của một bên thứ ba cụ thể nào không.\n" +
                "\t2.\tChúng tôi có thể tiết lộ thông tin như vậy cho các công ty và cá nhân mà chúng tôi thuê để thay mặt chúng tôi thực hiện các chức năng của công ty. Ví dụ, việc lưu giữ các máy chủ web, phân tích dữ liệu, cung cấp các trợ giúp về marketing, xử lý thẻ tín dụng hoặc các hình thức thanh toán khác, và dịch vụ cho khách hàng. Những công ty và cá nhân này sẽ truy cập tới thông tin cá nhân của bạn khi cần để thực hiện các chức năng của họ, nhưng không chia sẻ thông tin đó với bất kỳ bên thứ ba nào khác.\n" +
                "\t3.\tChúng tôi có thể tiết lộ thông tin như vậy nếu có yêu cầu pháp lý, hay từ một cơ quan chính phủ hoặc nếu chúng tôi tin rằng hành động đó là cần thiết nhằm: (a) tuân theo các yêu cầu pháp lý hoặc chiếu theo quy trình của luật pháp; (b) bảo vệ các quyền hay tài sản của Navigos Group, Ltd, hoặc các công ty đối tác; (c) ngăn chặn tội phạm hoặc bảo vệ an ninh quốc gia; hoặc (d) bảo vệ an toàn cá nhân của những người sử dụng hay công chúng.\n" +
                "\t4.\tChúng tôi có thể dùng tên bạn, tên hay logo của công ty bạn, hay thông tin khác về hoặc từ các quảng cáo tuyển dụng hoặc tài khoản xem hồ sơ ứng viên của bạn cho bất kỳ hay tất cả các mục đích tiếp thị. Ví dụ, các tên hay logo công ty có thể được dùng trong quảng cáo trên báo, thư gửi trực tiếp, phương tiện bán hàng, áp phích quảng cáo và các tài liệu khác liên quan đến App BaBaQ.\n" +
                "Chúng tôi cũng có thể chia sẻ thông tin vô danh về khách ghé thăm một trong các Web của công ty (ví dụ, số khách đến mục ‘Tìm việc’ của App BaBaQ) với các khách hàng, đối tác và bên thứ ba khác để họ có thể hiểu về các loại khách tới thăm App của App BaBaQ và cách họ sử dụng site.\n" +
                "Chúng tôi có thể hỗ trợ công nghệ, lưu trữ Web và các dịch vụ liên quan khác cho các công ty hàng đầu khác để thiết lập mục tuyển dụng trên App của họ (đôi khi được gọi là “khu vực tuyển dụng”). Thông tin cá nhân và/hoặc có tính nhân khẩu học do bạn cung cấp trong các khu vực tuyển dụng trở thành một phần của cơ sở dữ liệu của App BaBaQ, nhưng không ai có thể truy cập trừ bạn, chúng tôi và công ty có liên quan mà không có sự đồng ý của bạn.\n" +
                "Thông tin được thu thập trên trang thuộc khu vực tuyển dụng, hoặc trên trang chia sẻ nhãn hiệu (như trang về một cuộc thi do App BaBaQ và công ty khác đồng tài trợ) có thể trở thành tài sản của công ty đó, hoặc của cả chúng tôi và công ty đó. Trong ví dụ này, việc sử dụng thông tin như vậy của công ty kia có thể phụ thuộc vào chính sách bảo mật của công ty đó và, trong trường hợp bất kỳ nào, Navigos Group, Ltd không chịu trách nhiệm về việc công ty kia sử dụng thông tin cá nhân và nhân khẩu học của bạn.\n";

        titleF = "Bạn có thể truy nhập, cập nhật và xoá thông tin của bạn như thế nào";
        contentF = "Chúng tôi sẽ cung cấp cho bạn các phương tiện đảm bảo thông tin cá nhân của bạn là chính xác và cập nhật. Bạn có thể hiệu chỉnh hoặc xoá hồ sơ của bạn bất cứ lúc nào khi nhấn vào liên kết “Hồ sơ cá nhân” hoặc vào hình ảnh do hệ thống cung cấp ngay khi bạn đăng nhập vào. Khi đăng nhập vào hệ thống trong một khoảng thời gian nào đó, dù bạn đang ở đâu trên App tuyển người App BaBaQ, thông tin của bạn sẽ được giữ nguyên cho đến khi bạn nhấn chuột vào liên kết “Đăng xuất” là liên kết có thể truy nhập từ màn hình chính.\n" +
                "Nếu bạn là người sử dụng đã đăng ký và quên mật khẩu, bạn có thể nhận lại nó bằng cách gửi email và dùng tính năng “Quên mật khẩu”. Nhấn phím trên bất kỳ trang đăng nhập nào để yêu cầu gửi mật khẩu của bạn cho bạn. Chúng tôi không thể cung cấp mật khẩu của bạn theo các cách khác.\n" +
                "Tài khoản App BaBaQ của bạn có thể bị xoá, nhưng làm như vậy sẽ dẫn đến việc không thể truy nhập đến bất kỳ tính năng nào đòi hỏi đăng nhập. Chúng tôi sẽ hoặc có thể giữ một bản sao lưu trữ về tài khoản của bạn song không thể truy nhập trên Internet.\n";

        titleG = "Những biện pháp phòng ngừa an toàn chống mất mát, lạm dụng hoặc thay đổi thông tin của bạn";
        contentG = "Ngoài người quản trị App BaBaQ hoặc cá nhân được uỷ quyền khác của App BaBaQ ra, bạn là người duy nhất được truy nhập đến thông tin cá nhân của mình. Đăng ký sử dụng của bạn được bảo vệ bằng mật khẩu để ngăn chặn sự truy nhập trái phép.\n" +
                "Chúng tôi khuyến nghị bạn không để lộ mật khẩu của bạn cho bất kỳ ai. App BaBaQ không bao giờ hỏi mật khẩu của bạn qua điện thoại hay qua email tự nguyện. Để bảo đảm an toàn, bạn có thể muốn ra khỏi mạng ngay khi sử dụng xong App BaBaQ. Điều này đảm bảo những người khác không thể truy nhập tới thông tin và thư từ cá nhân của bạn nếu bạn dùng chung máy tính với ai đó hoặc dùng máy tính ở nơi công cộng như thư viện hay quán cà phê Internet.\n" +
                "Đáng tiếc là không có dữ liệu nào truyền trên Internet có thể bảo đảm an toàn 100%. Do vậy, mặc dù chúng tôi cố gắng hết sức bảo vệ thông tin cá nhân của bạn, App BaBaQ có thể không thể bảo đảm hoặc cam kết về tính an toàn của thông tin bất kỳ mà bạn chuyển tới chúng tôi hoặc từ dịch vụ trực tuyến của chúng tôi, và bạn phải tự chịu rủi ro. Ngay khi chúng tôi nhận được thông tin bạn gửi tới, chúng tôi sẽ cố gắng hết sức để bảo đảm an toàn trên hệ thống của chúng tôi.\n";

        titleH = "Cách App BaBaQ bảo vệ đời tư của trẻ em";
        contentH = "App BaBaQ là App có đối tượng độc giả lớn. Trẻ em sẽ phải xin phép bố mẹ trước khi gửi trực tuyến thông tin cá nhân tới ai đó. App BaBaQ không thể chia sẻ thông tin cá nhân về những người sử dụng dưới 13 tuổi với bên thứ ba. Ngoài ra, App BaBaQ sẽ không gửi bất kỳ email trực tiếp nào đề nghị người sử dụng thông báo họ dưới 13 tuổi.";

        titleI = "Bạn biết gì nữa về đời tư trực tuyến của bạn";
        contentI = "Hãy luôn nhớ rằng bất kỳ lúc nào bạn tự nguyện tiết lộ thông tin cá nhân của bạn trực tuyến, ví dụ qua công việc bạn đăng lên hay qua email, thông tin đó có thể bị người khác thu thập và sử dụng, Tóm lại, nếu bạn gửi thông tin cá nhân trực tuyến có thể truy nhập công khai, bạn có thể nhận sẽ được những thông báo tự nguyện từ những đối tác khác.\n" +
                "Cuối cùng, bạn phải tự chịu trách nhiệm về việc giữ bí mật cho mật khẩu và/hoặc các thông tin tài khoản bất kỳ. Vì thế, xin hãy cẩn thận và có trách nhiệm khi nào bạn ở trên mạng.\n";
    }

    private void getContentTextView(){
        TextView tvContentA = findViewById(R.id.tvContentA);
        tvContentA.setText(contentA);

        TextView tvTitleB = findViewById(R.id.tvTitleB);
        tvTitleB.setText(titleB);
        TextView tvContentB = findViewById(R.id.tvContentB);
        tvContentB.setText(contentB);

        TextView tvTitleC = findViewById(R.id.tvTitleC);
        tvTitleC.setText(titleC);
        TextView tvContentC = findViewById(R.id.tvContentC);
        tvContentC.setText(contentC);

        TextView tvTitleD = findViewById(R.id.tvTitleD);
        tvTitleD.setText(titleD);
        TextView tvContentD = findViewById(R.id.tvContentD);
        tvContentD.setText(contentD);

        TextView tvTitleE = findViewById(R.id.tvTitleE);
        tvTitleE.setText(titleE);
        TextView tvContentE = findViewById(R.id.tvContentE);
        tvContentE.setText(contentE);

        TextView tvTitleF = findViewById(R.id.tvTitleF);
        tvTitleF.setText(titleF);
        TextView tvContentF = findViewById(R.id.tvContentF);
        tvContentF.setText(contentF);

        TextView tvTitleG = findViewById(R.id.tvTitleG);
        tvTitleG.setText(titleG);
        TextView tvContentG = findViewById(R.id.tvContentG);
        tvContentG.setText(contentG);

        TextView tvTitleH = findViewById(R.id.tvTitleH);
        tvTitleH.setText(titleH);
        TextView tvContentH = findViewById(R.id.tvContentH);
        tvContentH.setText(contentH);

        TextView tvTitleI = findViewById(R.id.tvTitleI);
        tvTitleI.setText(titleI);
        TextView tvContentI = findViewById(R.id.tvContentI);
        tvContentI.setText(contentI);
    }
}
