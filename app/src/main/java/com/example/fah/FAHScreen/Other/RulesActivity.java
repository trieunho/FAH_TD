package com.example.fah.FAHScreen.Other;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.fah.R;

public class RulesActivity extends AppCompatActivity {
    private String titleA;
    private String titleB;
    private String titleC;
    private String titleD;
    private String titleE;
    private String titleF;
    private String titleG;
    private String contentA;
    private String contentB;
    private String contentC;
    private String contentD;
    private String contentE;
    private String contentF;
    private String contentG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        initContentData();
        getContentTextView();
    }

    private void initContentData(){
        titleA = "A. THỎA THUẬN SỬ DỤNG CHUNG";
        contentA = "   Bạn vui lòng đọc kỹ các Điều khoản & Điều kiện trong bản Thỏa thuận này này trước khi truy nhập và sử dụng dịch vụ trên App đăng tin tuyển dụng thời vụ." +
                " Bằng việc chấp thuận sử dụng dịch vụ của BaBaQ, bạn đồng ý bị ràng buộc bởi các quy định về sử dụng Dịch vụ trên App." +
                " Nếu bạn có bất kỳ câu hỏi nào về bản thỏa thuận này, vui lòng liên hệ chúng tôi qua email hotro@dut.com.\n" +
                "   Trường hợp có mâu thuẫn giữa Thỏa thuận này và hợp đồng của bạn với BaBaQ, Thỏa thuận này sẽ được ưu tiên áp dụng." +
                " Chúng tôi, với toàn quyền hạn của mình, có thể cập nhật, tạm dừng, chấm dứt, thay đổi bổ sung một phần hoặc toàn phần," +
                " mọi điều khoản được quy định trong Thỏa thuận theo thời gian mà không cần báo trước, vì các lý do pháp lý hoặc theo quy định hoặc để cho phù hợp với hoạt động của Cổng thông tin." +
                " Mọi thay đổi sẽ được thông báo cho bạn thông qua địa chỉ e-mail mà bạn cung cấp khi đăng ký hoặc thông qua một thông báo phù hợp trên trang App BaBaQ.\n";

        titleB = "B. QUYỀN VÀ TRÁCH NHIỆM CỦA NGƯỜI SỬ DỤNG";
        contentB = "1.\tBạn thừa nhận rằng tất cả thông tin hoặc dữ liệu thuộc bất kỳ dạng nào, dù là văn bản, phần mềm, mã, nhạc hoặc âm thanh, ảnh hoặc đồ họa," +
                    " video hoặc các chất liệu khác , được bạn (định nghĩa là người có tài khoản sử dụng) chia sẻ công khai và rộng rãi trên diễn đàn hoặc tại các khu vực trên App," +
                    " dưới bất kỳ định dạng nào, sẽ hoàn toàn thuộc trách nhiệm của bạn.\n" +
                "2.\tBạn thừa nhận rằng App của chúng tôi có thể đưa bạn đến Nội dung có thể không phù hợp hoặc phản cảm theo nhận định của cá nhân bạn." +
                    " Mặc dù vậy, chúng tôi sẽ không chịu bất kỳ trách nhiệm với bạn trong bất cứ phương diện nào nào về nội dung của trang App hay bất cứ tổn thất hoặc lỗi nào có liên quan." +
                    " Bạn có quyền từ chối nhận những thông tin tương tự bằng cách phản hồi tại địa chỉ: hotro@dut.com.\n" +
                "3.\tBạn sẽ không được quyền xâm phạm, xâm nhập, tiếp cận, sử dụng hay tìm cách xâm phạm, xâm nhập, tiếp cận hoặc sử dụng bất kỳ phần nào trong máy chủ hoặc mạng lưới của chúng tôi," +
                    " và/ hoặc bất kỳ khu vực dữ liệu nào nếu không được chúng tôi cho phép; bao gồm nhưng không giới hạn việc tác động nhằm hạn chế" +
                    " hoặc cấm đoán bất kỳ người dùng nào khác sử dụng và trải nghiệm dịch vụ của BaBaQ.\n" +
                "4.\tBạn sẽ không được tải lên, truyền và phát tán bất kỳ thông tin bất hợp pháp, lừa gạt, bôi nhọ, sỉ nhục, tục tĩu, khiêu dâm, xúc phạm, đe dọa, lăng mạ, thù hận, kích động…" +
                    " hoặc trái với chuẩn mực đạo đức chung của xã hội thuộc bất kỳ loại nào, bao gồm cả việc truyền bá" +
                    " hay khuyến khích những hành vi có thể cấu thành tội phạm hay vi phạm bất cứ điều khoản nào của luật pháp địa phương, quốc gia hay quốc tế." +
                    " Chúng tôi tôn trọng quyền tự do ngôn luận, nhưng cũng bảo lưu việc có toàn quyền lược bớt, hoặc xoá bỏ một phần hoặc toàn bộ các bài viết của bạn" +
                    " nếu xét thấy bài viết hoặc bất kỳ thông tin gì gửi lên vi phạm những điểm nêu trên, bất kể việc vi phạm đó là rõ ràng hay chỉ là hàm ý.\n" +
                "5.\tBạn sẽ không gửi hoặc truyền bất kỳ thông điệp nào mang tính quảng cáo, mời gọi, thư dây chuyền," +
                    " cơ hội đầu tư hay bất kỳ dạng liên lạc có mục đích thương mại nào mà người dùng không mong muốn," +
                    " thư rác hay tin nhắn rác dưới bất kỳ hình thức nào như email, thư, các cuộc gọi hay các bản fax.\n" +
                "6.\tBạn sẽ không gửi hoặc truyền bất kỳ thông tin hoặc phần mềm nào không thuộc quyền sở hữu của bạn trừ khi đó là phần mềm được cung cấp miễn phí," +
                    " không gửi bất kỳ thông tin hay phần mềm nào có chứa bất kỳ loại virus, Trojan, bọ hay các thành phần nguy hại nào tới hệ thống và người dùng.\n" +
                "7.\tBạn sẽ không gửi, xuất bản, truyền, tái sản xuất, phân phát hoặc khai thác bằng bất cứ hình thức nào với bất cứ thông tin nào thu được từ BaBaQ vào mục đích thương mại;" +
                    " hoặc tải lên, gửi, xuất bản, truyền, tái sản xuất hoặc phân phối dưới bất cứ hình thức nào những nội dung được bảo vệ bởi luật bản quyền và luật sở hữu trí tuệ của BaBaQ;" +
                    " hoặc tạo ra các biến thể của các nội dung đó mà không có sự đồng ý bằng văn bản của chủ nhân hoặc người giữ bản quyền.\n" +
                "8.\tBạn không có quyền trong nội dung và đối với thông tin được BaBaQ cung cấp, vì thế bạn sẽ không sử dụng thông tin trong trang App này với bất cứ hình thức nào" +
                    " hoặc bất cứ mục đích nào, trừ những điều khoản được xác lập trong bản Quy định sử dụng này.\n";

        titleC = "C. QUYỀN VÀ TRÁCH NHIỆM CỦA BABAQ";
        contentC = "1.\tDịch vụ của BaBaQ đóng vai trò là địa chỉ cho các nhà tuyển dụng đăng tuyển dụng, tìm kiếm và đánh giá Người tìm việc; Người tìm việc đăng Hồ sơ cá nhân," +
                    " tìm kiếm việc làm, các đơn vị quảng cáo đăng tải quảng cáo.\n" +
                "2.\tBaBaQ không tham gia vào các giao dịch thực tế giữa nhà tuyển dụng và Người tìm việc." +
                    " Do đó, BaBaQ không chịu trách nhiệm đối với những vấn đề phát sinh giữa Nhà tuyển dụng và Người tìm việc.\n" +
                "3.\tBaBaQ cố gắng đảm bảo các tài liệu đăng trên trang App BaBaQ được chính xác, uy tín và chất lượng cao," +
                    " nhưng chúng tôi không thực hiện bất kỳ bảo đảm, bảo lãnh nào liên quan đến nội dung đó.\n" +
                "4.\tBaBaQ không chịu trách nhiệm trước những lỗi hoặc phát sinh hoặc bất cứ bất tiện nào gây ra cho người sử dụng bởi bên thứ 3 không trực thuộc BaBaQ" +
                    " (nhà cung cấp mạng cho người sử dụng, đường truyền, băng thông phía người sử dụng.v.v.)\n" +
                "5.\tChúng tôi không thể bảo đảm trang App BaBaQ sẽ không có thiếu sót, không lỗi, hoặc máy chủ và trang App BaBaQ không chứa virus hoặc các cơ chế nguy hại khác;" +
                    " việc truy cập của bạn vào trang App BaBaQ thỉnh thoảng có thể bị giới hạn cho việc sửa chữa, bảo trì hoặc giới thiệu nội dung, các tiện ích hoặc dịch vụ mới." +
                    " Chúng tôi sẽ cố gắng nỗ lực phục hồi quyền truy cập và/hoặc dịch vụ hợp lý và sớm nhất có thể.\n" +
                "6.\tVi phạm an ninh mạng có thể dẫn đến bị truy cứu trách nhiệm dân sự và/hoặc hình sự." +
                    " BaBaQ sẽ điều tra các sự cố có thể dính líu đến việc vi phạm và có thể tham gia, và hợp tác với, các cơ quan thi hành án trong việc truy tố Người dùng tham gia các vi phạm này." +
                    " Chúng tôi có toàn quyền, vào mọi lúc, cấm hoặc từ chối truy cập của bạn vào BaBaQ hoặc bất kỳ phần nào của App ngay lập tức" +
                    " và không cần báo trước nếu chúng tôi cho rằng bạn đã vi phạm bất cứ điều khoản nào trong bản Quy định này, hoặc việc cấm truy cập xuất phát từ nhận định của chúng tôi," +
                    " khi chúng tôi cho rằng từ chối đó phù hợp và cần thiết trong thẩm quyền của chúng tôi.\n" +
                "7.\tBaBaQ được quyền bổ sung, sửa đổi hay xóa bỏ bất kỳ thông tin nào cũng như thay đổi giao diện, sự trình bày, thành phần, cấu trúc hoặc chức năng," +
                    " nội dung của trang App BaBaQ, cũng như thay đổi quy định nhằm mục đích tối ưu và nâng cấp App mà không cần báo trước.\n" +
                "8.\tNhằm tạo điều kiện thuận lợi cho khách hàng, trang App BaBaQ bao gồm các đường dẫn đến các trang App khác hoặc các tài liệu ngoài tầm kiểm soát của chúng tôi." +
                    " BaBaQ không chịu trách nhiệm cho bất kỳ nội dung hoặc trang App bên ngoài trang App BaBaQ\n";

        titleD = "D. QUYỀN SỞ HỮU TRÍ TUỆ VÀ CÁC QUYỀN KHÁC";
        contentD = "1.\tTất cả quyền sở hữu trí tuệ tồn tại trong App BaBaQ đều thuộc về BaBaQ hoặc cấp phép hợp pháp cho BaBaQ sử dụng." +
                    " Theo đó, tất cả các quyền hợp pháp đều được đảm bảo. Chúng tôi, với toàn quyền hạn của mình và vào bất cứ lúc nào được quyền bổ sung," +
                    " sửa đổi hay xóa bỏ bất kỳ thông tin nào cũng như thay đổi giao diện, sự trình bày, thành phần hoặc chức năng," +
                    " nội dung của trang App này bao gồm bất kỳ khoản mục nào mà không cần báo trước. Trừ phi được sự đồng ý của BaBaQ," +
                    " bạn không được phép tải lên, gửi, xuất bản, tái sản xuất, truyền hoặc phân phát bằng bất cứ hình thức nào" +
                    " bất cứ thành phần nào của App BaBaQ hoặc tạo ra những bản sửa đổi của nội dung cung cấp trong BaBaQ.\n" +
                "2.\tTrong mọi trường hợp, chúng tôi được bảo lưu quyền xử lý các thông tin đăng tải cho phù hợp với thuần phong mỹ tục," +
                    " các quy tắc đạo đức và các quy tắc đảm bảo an ninh quốc gia, và chúng tôi có toàn quyền cho phép hoặc không cho phép bài viết của bạn xuất hiện" +
                    " hay tồn tại trên diễn đàn hoặc tại các khu vực được phép chia sẻ thông tin.\n" +
                "3.\tChúng tôi có toàn quyền, bao gồm nhưng không giới hạn trong các quyền tác giả, thương hiệu, bí mật kinh doanh" +
                    " và các quyền sở hữu khác mà chúng tôi có trong App BaBaQ, nội dung của App, và hàng hóa hoặc dịch vụ được cung cấp." +
                    " Việc sử dụng quyền và sở hữu của chúng tôi cần phải được chúng tôi cho phép trước bằng văn bản." +
                    " Ngoài việc cấp phép bằng văn bản, chúng tôi không cấp phép dưới bất kỳ hình thức nào khác cho dù đó là hình thức công bố hay hàm ý," +
                    " hoặc thông qua việc để bạn sử dụng dịch vụ. Và do vậy, bạn không có quyền sử dụng App hoặc dịch vụ của chúng tôi" +
                    " vào mục đích thương mại mà không có sự cho phép bằng văn bản của chúng tôi trước đó.\n" +
                "4.\tBạn đồng ý để chúng tôi tự do sử dụng, tiết lộ, áp dụng và sửa đổi bất kỳ ý tưởng, khái niệm, cách thức, đề xuất, gợi ý," +
                    " bình luận hoặc hình thức thông báo nào khác mà bạn cung cấp cho chúng tôi (“Phản hồi”) có liên quan tới BaBaQ và/hoặc dịch vụ của BaBaQ một cách hoàn toàn miễn phí." +
                    " Bạn từ bỏ và đồng ý từ bỏ bất kỳ quyền và yêu cầu với bất kỳ khoản tiền thưởng, phí, nhuận bút, lệ phí và hoặc các kiểu chi trả khác liên quan đến việc chúng tôi sử dụng," +
                    " tiết lộ, áp dụng, và/hoặc chỉnh sửa bất kỳ hoặc tất cả Phản hồi của bạn.\n" +
                "5.\tVới việc cung cấp Nội dung cho chúng tôi: Bạn đồng ý trao cho chúng tôi toàn bộ quyền và cấp phép (bao gồm cả các quyền về tinh thần hay các quyền cần thiết khác) để sử dụng," +
                    " trưng bày, tái sản xuất, chỉnh sửa, làm cho phù hợp, xuất bản, cung cấp, xúc tiến, dịch và tạo ra các phiên bản phái sinh hoặc tổ hợp khác," +
                    " một phần hoặc toàn bộ, trên phạm vi toàn cầu mà không đòi hỏi thù lao. Sự cấp phép này được áp dụng với bất cứ dạng thức, phương tiện," +
                    " công nghệ nào đã được biết đến hoặc phát triển sau này; bạn cần lưu ý rằng, các bài viết (post) trên diễn đàn," +
                    " hoặc tại các khu vực được phép có thể tiếp tục nằm trên App của chúng tôi ngay cả khi tài khoản của bạn đã bị xóa vì bất kỳ lý do gì.\n" +
                "6.\tBạn bảo đảm rằng bạn có tất cả các quyền pháp lý, tinh thần và các quyền khác cần thiết để cấp phép cho chúng tôi theo điều khoản được quy định trong mục này;" +
                    " Nếu bạn cho rằng quyền sở hữu của bạn đã bị sử dụng theo những cách thức vi phạm quyền tác giả," +
                    " bạn có thể liên hệ với nhân viên phụ trách vấn đề bản quyền của chúng tôi tại địa chỉ: hotro@dtu.com.\n" +
                "7.\tBạn thừa nhận và đồng ý rằng chúng tôi sẽ có quyền (nhưng không có nghĩa vụ), từ chối xuất bản hoặc loại bỏ," +
                    " hoặc ngăn truy cập tới bất kỳ Nội dung nào bạn cung cấp vào bất cứ lúc nào, với bất cứ lý do nào, có hoặc không có thông báo.\n";

        titleE = "E. THỎA THUẬN BỔ SUNG DÀNH CHO NHÀ TUYỂN DỤNG";
        contentE = "1.\tBaBaQ sẽ cấp cho bạn quyền có giới hạn, có thể chấm dứt, không độc quyền để truy cập và sử dụng trang App BaBaQ." +
                    " Điều này cho phép bạn xem và tải về một bản sao nội dung BaBaQ hoặc các tài liệu trên trang App BaBaQ chỉ với mục đích sử dụng trực tiếp liên quan đến tìm kiếm và tuyển dụng nhân sự.\n" +
                "2.\tĐể đảm bảo trải nghiệm an toàn và hiệu quả cho tất cả các khách hàng của chúng tôi," +
                    " BaBaQ có quyền giới hạn lưu lượng dữ liệu (bao gồm việc xem hồ sơ) mà có thể được truy cập bởi hoặc được cung cấp cho bạn trong bất kỳ khoảng thời gian nhất định." +
                    " Các giới hạn này có thể được điều chỉnh tuỳ theo điều kiện của BaBaQ theo thời gian.\n" +
                "3.\tBạn bị nghiêm cấm sử dụng thông tin trong cơ sở dữ liệu hồ sơ BaBaQ để bán hoặc quảng bá bất kỳ sản phẩm, dịch vụ hoặc thực hiện bất kỳ hành động nào khác," +
                    " theo nhận định của BaBaQ, là không phù hợp với điều khoản này, gây hiểu nhầm hoặc không đầy đủ, hoặc vi phạm quy định pháp luật.\n" +
                "4.\tBạn chịu trách nhiệm bảo mật tài khoản Nhà tuyển dụng của bạn, Hồ sơ cá nhân và mật khẩu của bạn. Bạn không được phép chia sẻ mật khẩu," +
                    " thông tin đăng nhập tài khoản, chia sẻ quyền lợi Nhà tuyển dụng được xác thực mất phí (bao gồm việc lọc, xem hồ sơ và đăng tin tuyển dụng) với bất kỳ bên nào khác," +
                    " cho dù là tạm thời hay lâu dài. Bạn sẽ phải chịu trách nhiệm pháp lý và/hoặc trách nhiệm cá nhân cho tất cả việc sử dụng các thông tin đăng ký," +
                    " mật khẩu tại trang App BaBaQ của bạn, bất kể việc sử dụng đó có được bạn cho phép hay không.\n" +
                "5.\tThông tin Nhà tuyển dụng đăng trên BaBaQ không được chứa đựng những nội dung:\n" +
                    "\ta.\tThông tin không xác thực, sai lệch, hoặc gây hiểu lầm.\n" +
                    "\tb.\tĐăng tải công việc không phù hợp với luật pháp hiện hành.\n" +
                    "\tc.\tĐăng tải những nội dung không liên quan đến việc tuyển dụng nhân sự, những công việc.\n" +
                    "\td.\tYêu cầu người tìm việc phải trả tiền đặt cọc, phí đặt chỗ, phí xử lý thông tin\n" +
                    "\te.\tBaBaQ không cho phép tuyển dụng nhiều hơn 1 vị trí công việc trong mỗi tin đăng không có thật hoặc vi phạm đạo đức, pháp luật." +
                        " Khoản chi phí tương tự khác, một cách trực tiếp hoặc gián tiếp, và hoặc yêu cầu người tìm việc mua hàng. tuyển.\n" +
                "\tf.\tBaBaQ có quyền loại bỏ bất cứ tài liệu nhà tuyển dụng hoặc nội dung từ bất kỳ trang App BaBaQ mà không phù hợp với bản điều khoản này.\n" +
                "6.\tNếu bất cứ lúc nào trong quá trình sử dụng Dịch vụ BaBaQ, bạn trình bày sai sự thật hoặc sử dụng thông tin mạo danh để tuyển dụng trên BaBaQ" +
                    " hoặc vi phạm các điều khoản này, BaBaQ có quyền đơn phương chấm dứt việc sử dụng các dịch vụ của bạn trên trang App BaBaQ.\n" +
                "7.\tBạn không được phép sử dụng cơ sở dữ liệu hồ sơ BaBaQ trong bất kỳ cách hoàn cảnh nào, mà theo sự đánh giá riêng của BaBaQ," +
                    " ảnh hưởng bất lợi đến việc kinh doanh, triển vọng kinh doanh, hoạt động của BaBaQ, chức năng của trang App BaBaQ, cơ sở dữ liệu hồ sơ BaBaQ," +
                    " gây trở ngại đến khả năng truy cập của các người dùng khác vào cơ sở dữ liệu hồ sơ.\n" +
                "8.\tBất cứ trường hợp vi phạm nào trong các điều khoản tại Thỏa thuận sử dụng này, bạn sẽ không được hoàn trả lại khoản thanh toán đã thanh toán (nếu có).\n";

        titleF = "F. BỒI THƯỜNG";
        contentF = "1.\tNgười sử dụng đồng ý bảo vệ, bồi hoàn và loại trừ chúng tôi cùng BaBaQ khỏi những nghĩa vụ pháp lý, tố tụng, tổn thất," +
                    " chi phí (bao gồm nhưng không giới hạn cả án phí) có liên quan tới hoặc phát sinh do sự vi phạm của bạn đối với bất kỳ điều khoản nào trong Thỏa thuận" +
                    " và hoặc sử dụng App BaBaQ hay các App internet khác đến hoặc từ BaBaQ hoặc truyền nội dung bất kỳ trên BaBaQ. Trong các trường hợp xét thấy cần thiết," +
                    " hoặc theo quy định của pháp luật chúng tôi có thể tham gia tố tụng, khiếu kiện hoặc đàm phán nhưng không thỏa thuận nào có thể gây ảnh hưởng đến quyền lợi" +
                    " hoặc cấu thành bổn phận của chúng tôi đối với bất kỳ tranh chấp nào được đưa ra và giải quyết cho dù chúng tôi có mặt để tham gia tố tụng" +
                    " nếu chúng tôi không đồng ý bằng văn bản trước đó.\n" +
                "2.\tChúng tôi có toàn quyền, với chi phí của chúng tôi và có báo trước cho bạn, đảm nhận việc biện hộ và kiểm soát bất cứ khiếu kiện hoặc tố tụng nào nếu xét thấy cần thiết." +
                    " Tuy nhiên, quyền này của chúng tôi sẽ không bị coi là nghĩa vụ, bao gồm nhưng không giới hạn\n";

        titleG = "G. GIỚI HẠN TRÁCH NHIỆM PHÁP LÝ VÀ ĐẢM BẢO";
        contentG = "1.\tViệc truy cập và sử dụng App BaBaQ do bạn hoàn toàn tự nguyện và đồng ý rằng bạn chịu trách nhiệm, chi phí sử dụng (nếu có) về các thông tin bạn cung cấp trên BaBaQ.\n" +
                "2.\tKhi đăng tải các tài liệu, thông tin của bạn (thông tin, hồ sơ tìm việc, tin tuyển dụng v.v.) trên BaBaQ nhằm mục đích tuyển dụng hay tìm việc," +
                    " mặc nhiên bạn đồng ý rằng các tài liệu, thông tin của bạn ở chế độ không bảo mật được hiển thị phổ biến để đáp mục đích tuyển dụng/tìm việc," +
                    " có thể được bên thứ 3 sao chép mà chưa được sự đồng ý của BaBaQ.\n" +
                "3.\tBaBaQ không chịu trách nhiệm trước hành vi sao chép, nhân bản, sử dụng các tài liệu, thông tin của bạn của bên thứ 3 dù chưa được sự cho phép của BaBaQ.\n" +
                "4.\tBạn lưu ý rằng, chỉ mật khẩu tài khoản của bạn và những thông tin BaBaQ ghi rõ là bảo mật mới được BaBaQ bảo mật.\n" +
                "5.\tChúng tôi không phải là một thành phần tham gia vào bất kỳ giao dịch nào giữa bạn và một bên thứ ba. Trong bất kỳ trường hợp nào," +
                    " BaBaQ cũng không chịu trách nhiệm pháp lý về bạn hoặc bất kỳ người nào cho bất kỳ tổn thất nào, dù là trực tiếp, gián tiếp, bất ngờ, đặc biệt hoặc là hậu quả của sự kiện khác," +
                    " kể cả thiệt hại trong kinh doanh hoặc lợi nhuận, phát sinh từ việc sử dụng hoặc không thể sử dụng thông tin của BaBaQ" +
                    " ngay cả khi BaBaQ đã được khuyến cáo về khả năng xảy ra các tổn thất đó.\n" +
                "6.\tBạn hiểu và đồng ý rằng việc bạn sử dụng BaBaQ và bất cứ dịch vụ hoặc nội dung nào được cung cấp trong đó (sau đây gọi chung là Dịch vụ) đều thuộc phạm trù rủi ro riêng của bạn." +
                    " Các dịch vụ được cung cấp cho bạn \"như đang có\" và chúng tôi từ chối mọi bảo đảm thuộc bất kỳ dạng nào, được hàm ý hoặc công bố," +
                    " bao gồm nhưng không chỉ giới hạn trong các bảo hành về khả năng bán sản phẩm, sự phù hợp với một mục đích cụ thể nào đó, và sự không vi phạm.\n" +
                "7.\tBaBaQ không bảo đảm, dù hàm ý hay công bố, rằng mỗi phần của dịch vụ không bị gián đoạn, không có lỗi, không có virus, đúng giờ, an toàn, chính xác," +
                    " ổn định hay bất kỳ nội dung nào là an toàn cho bạn. Bạn hiểu và đồng ý rằng cả chúng tôi và những bên tham gia cung cấp dịch vụ đều không cung cấp những tư vấn chuyên nghiệp" +
                    " thuộc bất kỳ dạng nào và việc sử dụng những lời tư vấn đó hoặc bất kỳ thông tin nào khác đều thuộc vào quyết định riêng của bạn, với tất cả các yếu tố rủi ro của nó" +
                    " và không thuộc trách nhiệm của chúng tôi.\n" +
                "8.\tBạn đồng ý rằng bản Thỏa thuận sử dụng và bất kỳ bất đồng nào phát sinh từ việc bạn sử dụng App này hoặc các sản phẩm và dịch vụ của chúng tôi sẽ được giải quyết" +
                    " theo luật pháp hiện hành của Nước Cộng hoà Xã hội Chủ nghĩa Việt Nam. Thông qua việc đăng ký hoặc sử dụng App và dịch vụ của chúng tôi," +
                    " bạn mặc nhiên đồng ý và tuân thủ toàn bộ các quy định của Luật pháp Việt Nam.\n" +
                "9.\tTrong trường hợp một hoặc một số điều khoản của bản Thỏa thuận sử dụng này xung đột với các quy định của luật pháp và bị Tòa án coi là vô hiệu," +
                    " điều khoản đó sẽ được chỉnh sửa cho phù hợp với quy định của pháp luật hiện hành, và phần còn lại của Thỏa thuận sử dụng vẫn giữ nguyên giá trị.\n" +
                "10.\tViệc bất kỳ bên nào không thể chứng minh được về quyền của mình trong Thỏa thuận sử dụng sẽ không bị xem là việc từ bỏ quyền của bên đó" +
                    " và quyền này vẫn còn nguyên giá trị và hiệu lực.\n" +
                "11.\tBạn đồng ý rằng, bất kỳ khiếu tố hoặc tố tụng nào phát sinh từ App này phải được đệ trình trong vòng một (1) năm sau khi khiêu tố hoặc tố tụng ấy phát sinh," +
                    " nếu không khiếu tố sẽ hoàn toàn vô hiệu;\n" +
                "12.\tChúng tôi có thể chuyển nhượng quyền lợi và nghĩa vụ của mình chiểu theo bản Thỏa thuận sử dụng này và chúng tôi sẽ được giải phóng khỏi bất kỳ nghĩa vụ nào phát sinh sau đó.";
    }

    private void getContentTextView(){
        TextView tvTitleA = findViewById(R.id.tvTitleA);
        tvTitleA.setText(titleA);
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
    }
}
