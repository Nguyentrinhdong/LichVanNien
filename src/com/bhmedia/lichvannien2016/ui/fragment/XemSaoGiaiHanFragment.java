package com.bhmedia.lichvannien2016.ui.fragment;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bhmedia.lichvannien2016.R;
import com.bhmedia.lichvannien2016.ui.activity.KetQuaSaoGiaiHanActivity;
import com.bhmedia.lichvannien2016.ui.activity.KetQuaVanTrinhThangActivity;
import com.bhmedia.lichvannien2016.ui.activity.MyApp;
import com.bhmedia.lichvannien2016.ui.utility.CalendarUtil;
import com.bhmedia.lichvannien2016.ui.wheel.ArrayWheelAdapter;
import com.bhmedia.lichvannien2016.ui.wheel.NumericWheelAdapter;
import com.bhmedia.lichvannien2016.ui.wheel.OnWheelChangedListener;
import com.bhmedia.lichvannien2016.ui.wheel.OnWheelScrollListener;
import com.bhmedia.lichvannien2016.ui.wheel.WheelView;
import com.google.android.gms.analytics.HitBuilders;

public class XemSaoGiaiHanFragment extends BaseFragment {
	Context context;
	WheelView year1, year2, gioitinh;
	// year 1
	private int NoOfYear1 = 115;
	private int YesOfYear1 = 35;
	String years1[];
	// year 2
	private int NoOfYear2 = 115;
	private int YesOfYear2 = 35;
	String years2[];
	String manggioitinh[] = new String[] { "Nam", "Nữ" };
	// Wheel scrolled flag
	private boolean wheelScrolled = false;
	int posGioiTinh;
	ImageView iv_ketqua_xsgh;
	DateArrayAdapter arrayAdapterYear1, arrayAdapterYear2,
			arrayAdapterGioiTinh;
	String valueYear1, valueYear2;
	// EditText edkq;
	public static String thongtin;
	// TextView tv_current_year_xsgh;
	// private ImageView btnPreYear, btnNextYear;

	//
	private String arraySaoNam[] = { "La Hầu", "Thổ Tú", "Thuỷ Diệu",
			"Thái Bạch", "Thái Dương", "Vân Hớn", "Kế Đô", "Thái Âm", "Mộc Đức" };
	private String arraySaoNu[] = { "Kế Đô", "Vân Hớn", "Mộc Đức", "Thái Âm",
			"Thổ Tú", "La Hầu", "Thái Dương", "Thái Bạch", "Thuỷ Diệu" };
	private String arrayHanNam[] = { "Huỳnh Tiền", "Tam Kheo", "Ngũ Mộ",
			"Thiên Tinh", "Tán Tận", "Thiên La", "Địa Võng", "Diêm Vương" };
	private String arrayHanNu[] = { "Tán Tận", "Thiên Tinh", "Ngũ Mộ",
			"Tam Kheo", "Huỳnh Tiền", "Diêm Vương", "Địa Võng", "Thiên La" };
	private String arrayHanTuoi[][] = {
			{ "10", "18", "27", "36", "45", "54", "63", "72", "81" },
			{ "11", "19", "20", "28", "37", "46", "55", "64", "73", "82" },
			{ "12", "21", "29", "30", "38", "47", "56", "65", "74", "83" },
			{ "13", "22", "31", "39", "40", "48", "57", "66", "75", "84" },
			{ "14", "23", "32", "41", "49", "50", "58", "67", "76", "85" },
			{ "15", "24", "33", "42", "51", "59", "60", "68", "77", "86" },
			{ "16", "25", "34", "43", "52", "61", "69", "70", "78", "87" },
			{ "17", "26", "35", "44", "53", "62", "71", "79", "80", "88" } };

	private String arrayTENSAO[] = { "Thái Dương", "Thái Âm", "Mộc Đức",
			"Vân Hớn", "Thổ Tú", "Thái Bạch", "Thuỷ Diệu", "La Hầu", "Kế Đô" };
	private String arrayBINHGIAI[][] = {
			{
					"ngày 27 âm lịch hàng tháng.",
					"Thái dương tinh (măt trời) tốt vào tháng sáu, tháng mười, nhưng không hợp nữ giới. Chủ về an khang thịnh vượng, nam giới gặp nhiều tin vui, tài lộc còn nữ giới lại thường gặp tai ách.",
					"Hạn gặp Thái Dương nam hay nữ, trong tháng 6 và tháng 10 Âm Lịch sẽ gặp vận hanh thông, đắc tài hay đắc lộc.<br/><br/>Hành niên mà gặp Thái Dương,<br/>Đầu năm ắt đặng an khang tới cùng.<br/>Trai thời hoan hỉ trùng trùng,<br/>Gái e mắc phải nạn hung không chừng.<br/>Thái Dương ánh dọi trừng trừng,<br/>Không nơi nào chẳng sáng trưng rõ ràng.<br/>Đi xa tài lộc muôn vàn,<br/>Đại nhơn thấy mặt hân hoan vô ngần.<br/>Trong nhà thêm đặng miệng ăn,<br/>Hiệp hòa mọi sự, trở ngăn dứt rồi.<br/>Duy lo phận gái mà thôi,<br/>Chẳng như nam tử gặp hồi yên vui.<br/>Cúng sao khấn vái Kiến thiền,<br/>Ơn trên xuống phước khỏi phiền, khỏi lo.",
					"<div class=\"separator\"> <br> Thiên khí sao năm hạn:<br> ● Là sao chủ về hưng vượng tài lộc, tăng thêm nhân khẩu (hôn nhân hoặc sinh con). Có lợi cho nam, không hợp với nữ.<br> ● Sao này là một Phúc tinh thường chiếu mệnh cho bên nam giới. Những năm gặp sao Thái dương chiếu mệnh thì làm ăn được phát đạt, thăng quan, tiến chức, gặp may mắn trong việc buôn bán, nhất là vào tháng 6 và tháng 10 là hai tháng Đại cát.<br> ● Bên nữ giới gặp sao này chiếu mệnh thì có nhiều sự hân hoan, có bạn hữu giúp đở về tiền bạc hay làm ăn được nhiều thuận lợi, người đàn bà có thai cũng được bình an, đứa trẻ được khỏe mạnh, mỹ miều và duyên dáng<br> ● Các cô gái chưa chồng gặp sao này chiếu mệnh có thể có chồng năm đó<br> ● Người già cả trên 6, 7 mươi gặp sao này chiếu mệnh đau ốm nhẹ cũng khó qua khỏi<br> ● Đàn ông đi làm ăn đắc sáng suốt, đi xa có tài lợi đắc an khang<br> ● Đàn bà làm ăn hay tối tăm, tháng 6, tháng 10 tốt, có tài lợi.<br> Cách lễ nghinh sao:<br> ● Vào tối 27 hàng tháng, đặt ban thờ về hướng chính Đông. Trên ban thờ đặt 12 ngọn đèn (nến) bố trí theo các vị trí hướng sao hiện.<br> ● Bài vị: Dùng sớ viết tên …Giấy màu đỏ, viết tên Nhật Cung Thái Dương Tiên Tử Tinh Quân Vị Tiền<br> ● Cách khấn: Cung thỉnh thiên đình Uất Ly Cung Đại Thanh Đan Nguyên Hải Cung Thái Dương Tinh Quân Vị Tiền.</div>" },
			{
					"ngày 26 âm lịch hàng tháng.",
					"Chủ dương tinh (mặt trăng), tốt cho cả nam lẫn nữ vào tháng chín nhưng kỵ tháng mười. Nữ có bệnh tật, không nên sinh đẻ̉ e có nguy hiểm. Chủ về danh lợi, hỉ sự.",
					"Hạn gặp Thái Âm nam hay nữ, tháng 9 Âm Lịch sẽ gặp điều cát tường, nhưng tháng 11 Âm Lịch lại là tháng không tốt lắm.<br/><br/>Hành niên mà gặp Thái Âm,<br/>Sự gì cũng đặng toại tâm xứng tình.<br/>Cầu danh cầu lợi cho mình,<br/>Đi xa mới đặng tiến trình vẻ vang.<br/>Dầu mà kiện cáo đến quan,<br/>Chắc là đặng lý, mọi đàng vẹn xong.<br/>Đàn ông hành động khởi công,<br/>Việc gì cũng đặng trong vòng hân hoan.<br/>Đàn bà tật ách chẳng mang,<br/>Nhứt là sanh sản bệnh mang ngặt nghèo.",
					".<br> Thái Âm:<br> <br> Thiên khí sao năm hạn:<br> <br> ● Sao chủ về sự toại nguyện về danh lợi. Phụ nữ cần đề phòng về thai sản.<br> ● Sao này thường đem lại cho nữ giới sự điều hòa, vui vẻ, hạnh phúc, tiền tài, làm cho được toại nguyện những ước mơ của mình<br> ● Người đàn bà gặp sao Thái âm chiếu mệnh mà có thai nghén, nếu sinh con gái thì nết na thùy mỹ, duyên dáng, nghiêm trang, sau này sẽ trở thành một thiếu nữ diễm kiều, có thể là một trang quốc sắc thiên hương<br> ● Còn sinh con trai thì lại có tính tình nguội lạnh, bi quan, hiền hậu, đa cảm, ít nói, thích nghiên cứu những môn học khó khăn, có thể trở thành nhà toán học, triết học hay tu sĩ<br> ● Còn bên nam giới gặp sao này chiếu mệnh thì được bạn bè phái nữ giúp đở nhất là về tiền bạc vì sao này còn gọi là tài tinh<br> ● Người chưa lập gia đình sẽ gặp những cuộc tình duyên kỳ ngộ hay sẽ có vợ vào năm này<br> ● Đàn ông làm việc chi cũng đắc vừa ý, cầu danh tốt, cầu tài có tài lợi<br> ● Tháng 9 làm ăn phát tài, tháng 11 kỵ<br> <br> Cách lễ nghinh sao:<br> ● Vào tối 26 âm lịch hàng tháng đặt ban thờ về hướng chính Tây, trên ban thờ đặt 7 ngọn đèn (nến) bố trí theo các vị trí hướng sao hiện.<br> ● Bài vị: Dùng sớ viết tên. ..&nbsp; Giấy &nbsp;màu vàng, viết: Nguyệt Cung Thái Âm Hoàng Hậu Tinh Quân Vị Tiền<br> ● Cách khấn: Cung thỉnh thiên đình Kết Lâm Cung Đại Thánh Đô Diệu Nguyệt Phủ Thái Âm Tinh Quân Vị Tiền.<br>" },
			{
					"ngày 25 âm lịch hàng tháng.",
					"Triều ngươn tinh, chủ về hôn sự, nữ giới đề phòng tật bệnh phát sinh nhất là máu huyết, nam giới coi chừng bệnh về mắt. Tốt vào tháng mười và tháng chạp âm lịch.",
					"Hạn gặp Mộc Đức nam hay nữ, tháng Chạp là tháng tốt nhất trong năm.<br/><br/>Hành niên mà gặp Mộc Tinh,<br/>Đàn bà chẳng lợi giữ gìn tốn hao.<br/>Tuy là chút đỉnh đớn đau,<br/>Năm này tháng nọ việc nào cũng xuôi.<br/>Đàn ông con mắt đau rồi,<br/>Đàn bà bệnh máu lôi thôi có gì.<br/>Hôn nhân hòa hiệp tương nghi,<br/>Trong nhà già trẻ đều thì bình an.",
					"<div>Mộc Tinh:<br> Thiên khí sao năm hạn:<br> <br> ● Sao chủ về sự yên vui hòa hợp<br> ● Sao này cũng là một Phúc tinh cho cả nam và nữ<br> ● Người được sao Mộc tinh chiếu mệnh làm ăn gặp nhiều may mắn, có bạn mới, được thăng quan tiến chức, gặp quý nhơn giúp đở, đi thi cũng đậu, làm nhà cũng tốt, mở bày việc làm ăn không bị trở ngại<br> ● Đàn bà có thai gặp sao Mộc tinh chiếu mệnh thì đứa trẻ sinh ra dẫu trai hay gái tính tình cũng cương nghị, quả quyết, nhẫn nại, điềm tỉnh, đứa trẻ sẽ được nổi danh sau này<br> ● Nếu người thuộc mệnh Kim hay mệnh Mộc gặp sao này chiếu mệnh thì có phần trở ngại nhưng sao này là một Phúc tinh nên không hại gì<br> ● Đàn ông hay bị đau con mắt, cưới gả tốt, ăn nói bình an, có tài lợi<br> ● Đàn bà tháng chạp làm ăn phát tài lợi khá<br> <br> Cách lễ nghinh sao:<br> <br> ● Vào tối 25 âm lịch hàng tháng đặt ban thờ về hướng chính Đông, trên ban thờ đặt 20 ngọn đèn (nến) bố trí theo các vị trí hướng sao hiện.<br> ● Bài vị: Dùng sớ viết tên. ..&nbsp; Giấy &nbsp;màu xanh, viết: Đông Phương Giáp Ất Mộc Đức Tinh Quân Vị Tiền.<br> ● Cách khấn: Cung thỉnh thiên đình Thanh Văn Cung Đại Thánh Trung Quang Triều Nguyên Mộc Tinh Quân Vị Tiền.</div>" },
			{
					"ngày 29 âm lịch hàng tháng.",
					"Tai tinh, chủ về tật ách, xấu vào tháng hai và tháng tám âm lịch. Nam gặp tai hình, phòng thương tật, bị kiện thưa bất lợi; nữ không tốt về thai sản.",
					"Hạn gặp Văn Hớn nam hay nữ, nên đề phòng những chuyện thị phi khẩu thiệt trong tháng 2 và tháng 8 Âm Lịch.<br/><br/>Đầu năm mà gặp Hỏa Tinh,<br/>Giữ theo lối cũ lành mình mới xong.<br/>Gái nhiều tai nạn bận lòng,<br/>Trai lo hình vạ khó mong thoát vòng.<br/>Hỏa Tinh nhiều nỗi long đong,<br/>Sợ e thương tích phạm trong thân mình.<br/>Đàn bà giữ lúc thai sinh,<br/>Sợ e mắc phải tai tinh chẳng hiền.<br/>Đàn ông vạ phạt hình khiên,<br/>Nhiều điều bất lợi bạc tiền tiêu tan.<br/>Trong nhà gia quyến chẳng an,<br/>Có nuôi lục súc phò toan vẹn toàn.",
					"<div>Hỏa tinh<br> Thiên khí sao năm hạn:<br> ● Sao chủ về sự thủ cựu. Cần đề phòng thương tật, thai sản, nóng nảy, nói năng cẩn thận, tránh kiện tụng.<br> ● Sao này vẫn hiền lành, đàn ông hay đàn bà gặp sao này chiếu mệnh, mọi việc làm ăn đều được trung bình, chỉ có kỵ về khẩu thiệt vào tháng 2 và tháng 8<br> ● Đàn ông phòng quan sự, ăn nói nên lựa lời, nuôi thú vật bất lợi.<br> ● Kỵ tháng 2, tháng 8<br> <br> Cách lễ nghinh sao:<br> ● Vào tối 29 âm lịch hàng tháng, đặt ban thờ về hướng chính Nam, trên ban thờ đặt 15 ngọn đèn (nến) bố trí theo các vị trí hướng sao hiện.<br> ● Bài vị: Dùng sớ viết tên …Giấy màu đỏ, viết: Nam Phương Bính Đinh Hỏa Đức Tinh Quân Vị Tiền<br> ● Cách khấn: Cung thỉnh thiên đình Minh Ly Cung Đại Thánh Hỏa Đức Vân Hán Tinh Quân Vị Tiền.</div>" },
			{
					"ngày 19 âm lịch hàng tháng.",
					"Ách Tinh, chủ về tiểu nhân, xuất hành đi xa không lợi, có kẻ ném đá giấu tay sinh ra thưa kiện, gia đạo không yên, chăn nuôi thua lỗ. Xấu tháng tư, tháng tám âm lịch.",
					"Hạn gặp Thổ Tú nam hay nữ, có thể gặp những chuyện buồn trong tháng 4 và tháng 8 Âm Lịch.<br/><br/>Hành niên mà gặp Thổ Tinh,<br/>Việc quan sẽ có đến mình chẳng sai.<br/>Ra vô chẳng đặng hòa hài,<br/>Đề phòng kẻo mắc những loài tiểu nhân.<br/>Năm này tai họa nhiều cơn,<br/>Trong nhà xào xáo thua hơn bất hòa.<br/>Đêm nằm mộng mị thấy ma,<br/>Súc loài chẳng lội ra xa bất thường.",
					"<br> Thiên khí sao năm hạn:<br> ● Sao chủ về lòng trắc ẩn, đề phòng tiểu nhân, xuất nhập không thuận tiện.<br> ● Rất hợp cho cả đàn ông và đàn bà thuộc mệnh Thổ, nhưng năm gặp sao này chiếu mệnh thì trong tâm của người đó cảm thấy nổi buồn man mác, không có chủ định vững vàng, thường hay hoài nghi công việc làm ăn, không có hăng hái, song không gặp tai họa gì<br> ● Người già cả năm gặp sao này mà bị bệnh thì lâu khỏi.<br> ● Vì sao này là hung tinh, đi đâu cũng không thuận ý, đề phòng kẻ tiểu nhân, gia đạo không an, hay có chiêm bao mị mộng, kỵ nuôi thú, kỵ tháng 4, tháng 8, có việc lo buồn.<br> <br> Cách lễ nghinh sao:<br> ● Vào tối 19 âm lịch hàng tháng, đặt ban thờ về hướng chính Tây, trên ban thờ đặt 5 ngọn đèn (nến) bố trí theo các vị trí hướng sao hiện.<br> ● Bài vị: Dùng sớ viết tên …Giấy màu vàng, viết tên Trung Ương Mậu Kỷ Thổ Tú Tinh Quân Vị Tiền<br> ● Cách khấn: Cung thỉnh thiên đình Hoàng Đại Thanh Thổ Địa, Địa La, Thổ Tú Tinh Quân Vị Tiền.<br>" },
			{
					"ngày 15 âm lịch hàng tháng.",
					"Triều dương tinh, sao này xấu cần giữ gìn trong công việc kinh doanh, có tiểu nhân quấy phá, hao tán tiền của, đề phòng quan sự. Xấu vào tháng năm âm lịch và kỵ màu trắng quanh năm.",
					"Hạn gặp Thái Bạch nam hay nữ, không nên khuếch trương cơ sở thương mại đã có sẵn hoặc dự tính những kế hoạch đầu tư lớn lao.<br/><br/>Hành niên mà gặp Kim Tinh,<br/>Muôn điều chẳng đặng thỏa tình việc chi.<br/>Trai sầu muộn chết có khi,<br/>Gái thì rất kỵ tai nguy không ngần.<br/>Quí nhơn thấy mặt vui mừng,<br/>Trong nhà lại đặng miệng ăn thêm người.<br/>Hôn nhân chớ khá dễ ngươi,<br/>Sợ e bụng dạ vướng thì bệnh nguy.<br/>Ra vào phòng việc bất kỳ,<br/>Tiểu nhân mưu hại, ưu bi khá phòng.",
					"<div>Kim tinh:<br> Thiên khí sao năm hạn:<br> <br> ● Sao chủ về hao tài tốn của, không xứng ý toại lòng, đề phòng tai nạn bệnh tật.<br> ● Sao này rất hung tợn hơn sao La Hầu, những người thuộc mệnh Kim, mệnh Mộc và mệnh Hỏa đều Đại kỵ<br> ● Chẳng biết bao nhiêu người bị tai nạn, tù tội, mất chức hoặc chết trong những năm có sao Thái bạch chiếu mệnh<br> ● Cho đến việc làm nhà trong năm gặp sao Thái bạch chiếu mệnh cũng không tốt<br> ● Nếu ai ăn ở mất âm đức, khi gặp sao này chiếu mệnh thì sẽ bị hoạn họa chẳng sai<br> ● Vì sao này có kiết, có hung<br> ● Đàn ông hay lo rầu rồi đắc khá, có quí nhân giúp đỡ<br> ● Đàn bà hay đau ốm, vợ chồng hay tranh cải, ngừa tiểu nhân<br> ● Kỵ tháng 5 và màu trắng, đàn bà nặng hơn<br> <br> Cách lễ nghinh sao:<br> <br> ● Vào tối 15 âm lịch hàng tháng, đặt ban thờ về hướng chính Tây, trên ban thờ đặt 8 ngọn đèn (nến) bố trí theo các vị trí hướng sao hiện.<br> ● Bài vị: Dùng sớ viết tên …Giấy màu trắng, viết: Tây Phương Canh Tân Kim Đức Thái Bạch Tinh Quân Vị Tiền<br> ● Cách khấn: Cung thỉnh thiên đình Hao Linh Cung Đại Thánh Kim Đức Thái Bạch Tinh Quân Vị Tiền.</div>" },
			{
					"ngày 21 âm lịch hàng tháng.",
					"Phước lộc tinh, tốt nhưng cũng kỵ tháng tư và tháng tám. Chủ về tài lộc hỉ. Không nên đi sông biển, giữ gìn lời nói (nhất là nữ giới) nếu không sẽ có tranh cãi, lời tiếng thị phi đàm tiếu.",
					"Sao Thủy Diệu thuộc ngũ hành thủy, là sao phúc lộc, cơ hội làm ăn phát triển trở lại, có thể đầu tư phát triển sản xuất kinh doanh. Nam giới gặp vận hội làm ăn, giao du bạn bè có tài có lộc, Nữ giới nhiều tai nạn, nhất là chuyện khẩu thiệt tranh cãi kiện tụng. Kỵ tháng 4, 8.Người mệnh Kim và Mộc hợp với hạn sao này, riêng người mệnh Hỏa thì hay bị khắc kỵ.<br/><br/>Thủy Diệu thuộc về Thủy Tinh<br/>Trong năm nhịn nhục chống kình hiểm nguy<br/>Đạo tặc phán phúc có khi<br/>Huyền Vũ chiếu mệnh lâm thì tà gian<br/>Nam nữ vận mệnh chẳng an<br/>Tháng Tư tháng Tám lụy trần bi ai<br/>Văn thơ khẩu thật có hoài<br/>Nói hành dụm miệng lo bày kế mưu<br/>Tang khó chẳng khỏi sầu ưu<br/>Mất đồ hao của phiêu lưu giữ gìn<br/>Gặp người quen lạ chớ tin<br/>Bạc tiền mượn hỏi chớ khinh mà lầm.",
					"Thiên khí sao năm hạn:<br> ● Sao chủ về tài lộc, vui vẻ, đề phòng sông nước và ăn nói.<br> ● Sao Thủy diệu rất hợp với người thuộc mệnh Mộc và mệnh Kim<br> ● Sao này thường đem đến sự bất ngờ và may mắn trong công việc làm ăn buôn bán<br> ● Người đàn bà có thai gặp sao này cũng được tốt và bình an<br> ● Những người thuộc mệnh Hỏa gặp sao này chiếu mệnh thì có phần trở ngại nhưng sao này chẳng phải là Hung tinh nên cũng không sao<br> ● Đàn ông đi làm ăn khá, đi xa có tài lợi<br> ● Đàn bà hay có tai nạn, kỵ đi sông sâu, kỵ tháng 4, tháng 8<br> <br> Cách lễ nghinh sao:<br> <br> ● Vào tối 21 âm lịch hàng tháng, đặt ban thờ về hướng chính Bắc, trên ban thờ đặt 7 ngọn đèn (nến) bố trí theo các vị trí hướng sao hiện.<br> ● Bài vị: Dùng sớ viết tên …Giấy màu đen, viết: Bắc Phương Nhâm Quí Thủy Đức Tinh Quân Vị Tiền.<br> ● Cách khấn: Cung thỉnh thiên đình Thủy Đức Kim Nữ Cung Đại Thánh Bắc Phương Nhâm Quí Thủy Diệu Tinh Quân Vị Tiền.<br>" },
			{
					"ngày 8 âm lịch hàng tháng.",
					"Khẩu thiệt tinh, chủ về ăn nói thị phi, hay liên quan đến công quyền, nhiều chuyện phiền muộn, bệnh tật về tai mắt, máu huyết. Nam rất kỵ, nữ cũng bi ai chẳng kém. Kỵ tháng giêng, tháng bảy.",
					"Hạn gặp La Hầu nam hay nữ, nên đề phòng những tai nạn có thể xảy ra trong tháng Giêng và tháng 7 Âm Lịch.<br/><br/>Hành niên mà gặp La Hầu,<br/>Chắc là trăm việc lo rầu không an.<br/>Trai thì tai vạ đến quan,<br/>Gái thì nhiều nỗi gian nan buồn phiền.<br/>Trai phòng khẩu thiệt hình khiên,<br/>Sợ e con mắt nhiễm truyền bệnh đau.<br/>Gái lo bệnh huyết dàu dàu,<br/>Thai sanh đẻ quỉ, xiết bao hiểm nghèo.",
					"<div align=\"center\"> <div>&nbsp;</div> <div>&nbsp;</div> <div>La hầu:</div> <div>Thiên khí sao năm hạn:</div> <div>● Sao chủ về buồn rầu, chuyện thị phi, kiện tụng, tai ương.<br> ● Sao La hầu cũng có thể là một Hung tinh, thường đem đến cho người nổi buồn rầu, điều tai nạn, hoặc tang sự, kiện thưa hay mang tiếng thị phi.<br> ● Người nào mệnh Kim và Mộc thì chịu ảnh hưởng nặng hơn mệnh khác vì sao này thuộc Mộc.<br> ● Đàn ông gặp sao này chiếu mệnh mà người vợ có thai thì trong năm đó lại được hên may, làm ăn được phát đạt mà người vợ sinh sản cũng được bình an.<br> ● Vì sao này là hung tinh, nam thì ngừa quan sự, sinh rầy rà, nhiều đoạn ưu sầu, nữa hay có việc rầu buồn, đau máu, sinh dưỡng có bịnh, kỵ tháng Giêng, tháng Bảy<br> ● Kỵ đàn ông nhiều, kỵ đàn bà ít.</div> <div><br> Cách lễ nghinh sao:</div> <div>● Vào tối mồng 8 âm lịch hàng tháng, đặt ban thờ về hướng chính bắc, trên ban thờ đặt 9 ngọn đèn (nến) bố trí theo các vị trí hướng sao hiện.</div> <div>● Bài vị: Dùng sớ viết tên …Giấy màu vàng, viết: Thiên Cung Thần Thủ La Hầu Tinh Quân Vị Tiền.</div> ● Cách khấn: Cung thỉnh Thiên Hoàng Phán Cung Đại Thánh Thần Thủ La Hầu Tinh Quân Vị Tiền.<br></div>" },
			{
					"ngày 18 âm lịch hàng tháng.",
					"Hung tinh, kỵ tháng ba và tháng chín nhất là nữ giới. Chủ về ám muội, thị phi, đau khổ, hao tài tốn của, họa vô đơn chí; trong gia đình có việc mờ ám, đi làm ăn xa lại có tài lộc mang về.",
					"Hạn gặp Kế Đô nam hay nữ, có thể gặp những chuyện buồn thương trong tháng 3 và tháng 9 Âm Lịch.<br/><br/>Hành niên mà gặp Kế Đô,<br/>Liên miên tai nạn chẳng giờ nào không.<br/>Đàn bà khẩu thiệt khá phòng,<br/>Đàn ông may đặng thoát vòng tai ương.<br/>Đại nhơn thấy mặt chẳng thương,<br/>Ai nuôi mục đức thăng thường hay hao.<br/>Đàn bà khẩu thiệt thấp cao,<br/>Làm ăn xứ lạ, biềt bao lỗi tài.<br/>Bằng mà cứ ở nhà hoài,<br/>Nhiều điều ám muội khó rày biện phân.",
					"<div>Kế đô:<br>  Thiên khí sao năm hạn:<br> <br> ● Sao chủ về hung dữ, chuyện thị phi, ám muội, buồn rầu.<br> ● Sao này rất đại kỵ cho cả nam lẫn nữ<br> ● Đây là một Hung tinh, thường đem lại sự buồn khổ, chán nản, thờ ơ<br> ● Đàn ông làm ăn bình thường, đi xa có tài lợi<br> ● Người đàn ông mê gái, gặp sao này chiếu mệnh nên đề phòng mang tai tiếng vì đàn bà.<br> ● Nhưng đàn bà khi gặp sao này chiếu mệnh mà có thai thì lại hên may lạ thường, cái hên nó còn ảnh hưởng đến cả người chồng nữa.<br> ● Khi sinh sản cũng được yên lành<br> ● Nếu không có thai nghén thì người đàn bà sẽ bị lao đao lận đận lắm, làm ăn gặp nhiều trở ngại<br> ● Đàn bà hay xảy ra việc rày rà, mang tiếng thị phi<br> ● Kỵ tháng 3, tháng 9, hoặc có việc ai bi<br> ● (Đại kỵ cho nữ giới, nhất là tháng 3 và tháng 9)<br> <br> Cách lễ nghinh sao:<br> ● Vào tối 18 âm lịch hàng tháng, đặt ban thờ về hướng chính tây, trên ban thờ đặt 21 ngọn đèn (nến) bố trí theo các vị trí hướng sao hiện.<br> ● Bài vị: Dùng sớ viết tên… Giấy màu vàng, viết: Thiên Cung Phân Vĩ Kế Đô Tinh Quân Vị Tiền<br> ● Cách khấn: Cung thỉnh thiên đình Bắc Vĩ Cung Đại Thánh Thần Vĩ Kế Đô Tinh Quân Vị Tiền</div>" } };

	@Override
	protected int getLayoutId() {

		return R.layout.layout_fragment_xsgh;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		context = activity.getApplicationContext();
	}

	@Override
	protected void onInitializeView(Bundle savedInstanceState) {
		super.onInitializeView(savedInstanceState);

		MyApp.tracker().setScreenName("Xem Sao Giai Han Android");
		MyApp.tracker().send(new HitBuilders.AppViewBuilder().build());

		iv_ketqua_xsgh = (ImageView) findViewById(R.id.iv_ketqua_xsgh);
		// edkq = (EditText) findViewById(R.id.edkq);
		// edkq.setFocusable(false);
		iv_ketqua_xsgh.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				int hsecond = Integer.parseInt(valueYear2);
				int hfirst = Integer.parseInt(valueYear1);
				int hthird = posGioiTinh;

				// check input tuổi
				int tuoiAm = hsecond - hfirst + 1;
				if (tuoiAm < 10 | tuoiAm > 90) {
					Toast.makeText(
							getActivity(),
							"Số tuổi của bạn không được nhỏ hơn 10 hoặc lớn hơn 90",
							Toast.LENGTH_SHORT).show();
					// thông báo: số tuổi của bạn không được nhỏ hơn 10 hoặc lớn
					// hơn 90
					return;
				}

				// tính tên sao
				int indexArray = tuoiAm % 9;
				if (indexArray == 0) {
					indexArray = 8;
				} else {
					indexArray -= 1;
				}
				String tenSao = "";
				if (hthird == 0) {
					tenSao = arraySaoNam[indexArray];
				} else {
					tenSao = arraySaoNu[indexArray];
				}

				// tính tên hạn
				String tenHan = "";
				String soTuoi = String.valueOf(tuoiAm);
				if (hthird == 0) {
					for (int i = 0; i < arrayHanTuoi.length; i++) {
						String arr[] = arrayHanTuoi[i];
						if (Arrays.asList(arr).contains(soTuoi)) {
							indexArray = i;
							tenHan = arrayHanNam[indexArray];
						}
					}
				} else {
					for (int i = 0; i < arrayHanTuoi.length; i++) {
						String arr[] = arrayHanTuoi[i];
						if (Arrays.asList(arr).contains(soTuoi)) {
							indexArray = i;
							tenHan = arrayHanNu[indexArray];
						}
					}
				}

				// tính bình giải sao
				indexArray = Arrays.asList(arrayTENSAO).indexOf(tenSao);
				String binhGiaiS[] = arrayBINHGIAI[indexArray];
				String tinhNgayGiaiSao = binhGiaiS[0];
				String tinhChatSao = binhGiaiS[1];
				String tinhThoSao = binhGiaiS[2];
				String tinhBinhGiaiSao = binhGiaiS[3];

				// tính ảnh hiển thị
				String gioitinh = manggioitinh[posGioiTinh];
				String imgThapNen = "img_nen" + String.valueOf(indexArray)
						+ ".png";
				String imgBaiVi = "img_nen" + String.valueOf(indexArray)
						+ "_baivi.png";

				// câu khấn cuối cùng
				String khanvai = "- Con lạy chín phương Trời, mười phương Chư Phật, Chư Phật mười phương.<br/>- Nam mô Hiệu Thiên chí tôn Kim Quyết Ngọc Hoàng Thượng đế.<br/>- Con kính lạy Đức Trung Thiện tinh chúa Bắc cựcTử Vi Tràng Sinh Đại đế.<br/>- Con kính lạy ngài Tả Nam Tào Lục Ty Duyên Thọ Tinh quân.<br/>- Con kính lạy Đức Hữu Bắc Đẩu cửu hàm Giải ách Tinh quân.<br/>- Con kính lạy Đức Nhật cung Thái Dương Thiên tử Tinh quân.<br/>- Con kính lạy Đức Thượng Thanh Bản mệnh Nguyên Thần Chân quân.<br/>Tín chủ (chúng) con là:………………………………………. Tuổi:…………………………<br/>Hôm nay là ngày…… tháng………năm….., tín chủ con thành tâm sắm lễ, hương hoa trà quả, đốt nén tâm hương, thiết lập linh án tại (địa chỉ)…………………………để làm lễ giải hạn sao <b>"
						+ tenSao
						+ "</b> chiếu mệnh, và hạn <b>"
						+ tenHan
						+ "</b>.<br/>Cúi mong chư vị chấp kỳ lễ bạc phù hộ độ trì giải trừ vận hạn; ban phúc,lộc, thọ cho con gặp mọi sự lành, tránh mọi sự dữ, gia nội bình yên, an khang thịnh vượng.<br/>Tín chủ con lễ bạc tâm thành, trước án kính lễ, cúi xin được phù hộ độ trì.<br/>Phục duy cẩn cáo!<br/>(Và quỳ lạy theo số lạy của từng sao theo phần \"Tính chất sao Cửu Diệu....\" đã nói ở trên)";
				String content = "<b>Sinh năm: "
						+ valueYear1
						+ " - "
						+ String.valueOf(tuoiAm)
						+ " tuổi.<br/>Giới tính: "
						+ gioitinh
						+ ".<br/>Xem sao chiếu năm: "
						+ valueYear2
						+ ".<br/>Sao chiếu mệnh là sao: "
						+ tenSao
						+ ".</b><br/><br/>"
						+ tinhChatSao
						+ "<br/><br/>"
						+ tinhThoSao
						+ "<br/><br/>"
						+ tinhBinhGiaiSao
						+ "<br/><b>Cúng dâng sao "
						+ tenSao
						+ ".</b><br/><b>Cúng "
						+ tinhNgayGiaiSao
						+ "</b><p>Thắp nến - Bài vị</b></p><div id=\"images\"><img class=\"fblogo\" src=\"images/"
						+ imgThapNen
						+ "\" width=\"140\" height=\"190\"/><img class=\"fblogo\" src=\"images/"
						+ imgBaiVi
						+ "\" width=\"140\" height=\"190\"/></div><br/>"
						+ khanvai + "\"";
				// thongtin =
				// "\"<style>div{color:#333333; font-size:16px; text-align:justify;}.fblogo {display: inline-block;margin-left: 1px;margin-right: 1px;height: 190px;}#images{text-align:center;}</style>\"<div>"
				// + content + "</div>";
				thongtin = content;
				Intent it = new Intent(getActivity(),
						KetQuaSaoGiaiHanActivity.class);
				startActivity(it);

				// edkq.setText(content);

				// Bundle mBundle = new Bundle();
				// if (valueYear1 != null && valueYear2 != null) {
				// wheelScrolled = false;
				// mBundle.putString("NAM1", valueYear1);
				// mBundle.putString("NAM2", valueYear2);
				// } else {
				// Date dateCurr = new Date();
				// Calendar calendar1 = Calendar.getInstance();
				// calendar1.setTime(dateCurr);
				// mBundle.putString("NAM", calendar1.get(Calendar.YEAR) + "");
				// mBundle.putString("THANG",
				// (calendar1.get(Calendar.MONTH) + 1) + "");
				// mBundle.putString("NGAY",
				// calendar1.get(Calendar.DAY_OF_MONTH) + "");
				// }
				//
				//
				// mBundle.putInt("GioiTinh", posGioiTinh);
				// // mBundle.putString("NAM_XEM",
				// tv_current_year_vtt.getText().toString());
				//
				// Intent intent = new Intent(getActivity(),
				// KetQuaVanTrinhThangActivity.class);
				// intent.putExtra("Bundle", mBundle);
				// startActivity(intent);
			}
		});
		Calendar calendar = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar2.set(Calendar.MONTH, 6);
		calendar2.set(Calendar.YEAR, 1987);
		calendar2.set(Calendar.DAY_OF_MONTH, 24);
		// day = (WheelView) findViewById(R.id.day_vtt);
		// month = (WheelView) findViewById(R.id.month_vtt);
		year1 = (WheelView) findViewById(R.id.year1_xsgh);
		year2 = (WheelView) findViewById(R.id.year2_xsgh);
		gioitinh = (WheelView) findViewById(R.id.gioitinh_xsgh);

		// // month
		// int curMonth = calendar.get(Calendar.MONTH);
		// arrayAdapterMonth = new DateArrayAdapter(context, months, curMonth);
		// month.setViewAdapter(arrayAdapterMonth);
		// month.setVisibleItems(2);
		// month.setCurrentItem(curMonth);
		// month.addChangingListener(changedListener);
		// month.addScrollingListener(scrolledListener);

		Calendar cal = Calendar.getInstance();
		// int currDay = calendar.get(Calendar.DAY_OF_MONTH);
		// year 1
		int curYear1 = calendar2.get(Calendar.YEAR);
		int Year1 = calendar2.get(Calendar.YEAR);
		years1 = getListString(Year1 - NoOfYear1, Year1 + YesOfYear1);
		arrayAdapterYear1 = new DateArrayAdapter(context, years1, NoOfYear1);
		year1.setViewAdapter(arrayAdapterYear1);
		year1.setVisibleItems(2);
		year1.setCurrentItem(curYear1 - (Year1 - NoOfYear1));
		year1.addChangingListener(changedListener);
		year1.addScrollingListener(scrolledListener);
		// year 2
		int curYear2 = calendar.get(Calendar.YEAR);
		int Year2 = cal.get(Calendar.YEAR);
		years2 = getListString(Year2 - NoOfYear2, Year2 + YesOfYear2);
		arrayAdapterYear2 = new DateArrayAdapter(context, years2, NoOfYear2);
		year2.setViewAdapter(arrayAdapterYear2);
		year2.setVisibleItems(2);
		year2.setCurrentItem(curYear2 - (Year2 - NoOfYear2));
		year2.addChangingListener(changedListener);
		year2.addScrollingListener(scrolledListener);

		// gioi tinh
		CalendarUtil calendarUtil = new CalendarUtil();
		int[] convertgioitinh = calendarUtil.convertGioiTinh(manggioitinh);
		// int currMonthAL
		arrayAdapterGioiTinh = new DateArrayAdapter(context, manggioitinh,
				convertgioitinh[0]);
		gioitinh.setViewAdapter(arrayAdapterGioiTinh);
		gioitinh.setVisibleItems(2);
		gioitinh.setCurrentItem(convertgioitinh[0]);
		gioitinh.addChangingListener(changedListener);
		gioitinh.addScrollingListener(scrolledListener);

		// day
		/*
		 * day.setViewAdapter(new ArrayWheelAdapter(context, wheelMenu1));
		 * day.setVisibleItems(2);
		 */
		// updateDays(year, month, day);
		// day.setCurrentItem(calendar.get(Calendar.DAY_OF_MONTH) - 1);
		// day.addChangingListener(changedListener);
		// day.addScrollingListener(scrolledListener);

		// tv_current_year_vtt = (TextView)
		// findViewById(R.id.tv_current_year_vtt);

		// btnPreYear = (ImageView) findViewById(R.id.btn_pre_year);
		// btnNextYear = (ImageView) findViewById(R.id.btn_next_year);

		updateStatus();

		// btnPreYear.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		// if(tv_current_year_vtt.getText().equals("2015")){
		// tv_current_year_vtt.setText("2014");
		// btnPreYear.setVisibility(View.INVISIBLE);
		// btnNextYear.setVisibility(View.VISIBLE);
		// }
		// }
		// });

		// btnNextYear.setOnClickListener(new OnClickListener() {
		//
		// @Override
		// public void onClick(View arg0) {
		// // TODO Auto-generated method stub
		// if(tv_current_year_vtt.getText().equals("2014")){
		// tv_current_year_vtt.setText("2015");
		// btnNextYear.setVisibility(View.INVISIBLE);
		// btnPreYear.setVisibility(View.VISIBLE);
		// }
		// }
		// });
	}

	// Wheel scrolled listener
	OnWheelScrollListener scrolledListener = new OnWheelScrollListener() {
		public void onScrollStarts(WheelView wheel) {
			wheelScrolled = true;
		}

		public void onScrollEnds(WheelView wheel) {
			wheelScrolled = false;
			updateStatus();
			// updateDayOfMonth();
			// arrayAdapterDay.changeColorCurrValue(day.getCurrentItem());
			// arrayAdapterMonth.changeColorCurrValue(month.getCurrentItem());
			arrayAdapterYear1.changeColorCurrValue(year1.getCurrentItem());
			arrayAdapterYear2.changeColorCurrValue(year2.getCurrentItem());
			arrayAdapterGioiTinh
					.changeColorCurrValue(gioitinh.getCurrentItem());
		}

		@Override
		public void onScrollingStarted(WheelView wheel) {

		}

		@Override
		public void onScrollingFinished(WheelView wheel) {

		}
	};
	// Wheel changed listener
	private final OnWheelChangedListener changedListener = new OnWheelChangedListener() {
		public void onChanged(WheelView wheel, int oldValue, int newValue) {
			if (!wheelScrolled) {
				updateStatus();
				// updateDayOfMonth();
				// arrayAdapterDay.changeColorCurrValue(day.getCurrentItem());
				arrayAdapterYear1.changeColorCurrValue(year1.getCurrentItem());
				arrayAdapterYear2.changeColorCurrValue(year2.getCurrentItem());
				arrayAdapterGioiTinh.changeColorCurrValue(gioitinh
						.getCurrentItem());
			}
		}
	};

	private WheelView getWheel(int id) {
		return (WheelView) findViewById(id);
	}

	private int getWheelValue(int id) {
		return getWheel(id).getCurrentItem();
	}

	Calendar updateDays(WheelView year1, WheelView year2, WheelView gioitinh) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR,
				calendar.get(Calendar.YEAR)
						+ (year1.getCurrentItem() - NoOfYear1));
		// calendar.set(Calendar.MONTH, month.getCurrentItem());

		// int maxDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		// days = getListString(1, maxDays);
		// arrayAdapterDay = new DateArrayAdapter(context, days,
		// calendar.get(Calendar.DAY_OF_MONTH) - 1);
		// day.setViewAdapter(arrayAdapterDay);
		// int curDay = Math.min(maxDays, day.getCurrentItem() + 1);
		// day.setCurrentItem(curDay - 1, true);
		// calendar.set(Calendar.DAY_OF_MONTH, curDay);

		return calendar;

	}

	private class DateNumericAdapter extends NumericWheelAdapter {
		int currentItem;
		int currentValue;

		public DateNumericAdapter(Context context, int minValue, int maxValue,
				int current) {
			super(context, minValue, maxValue);
			this.currentValue = current;
			setTextSize(14);
		}

		@Override
		protected void configureTextView(TextView view) {
			super.configureTextView(view);
			if (currentItem == currentValue) {
				// view.setTextColor(Color.parseColor("#C4AC5C"));
				view.setTextColor(Color.parseColor("#1C9D51"));
			} else {
				view.setTextColor(Color.BLACK);
			}
			view.setTypeface(null, Typeface.NORMAL);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			currentItem = index;
			return super.getItem(index, cachedView, parent);
		}
	}

	private class DateArrayAdapter extends ArrayWheelAdapter<String> {
		int currentItem;
		int currentValue;

		public DateArrayAdapter(Context context, String[] items, int current) {
			super(context, items);
			this.currentValue = current;
			setTextSize(14);
		}

		public void changeColorCurrValue(int value) {
			currentValue = value;
			notifyDataChangedEvent();
		}

		@Override
		protected void configureTextView(TextView view) {
			super.configureTextView(view);
			if (currentItem == currentValue) {
				// view.setTextColor(Color.parseColor("#C4AC5C"));
				view.setTextColor(Color.parseColor("#1C9D51"));
			} else {
				view.setTextColor(Color.BLACK);
			}
			view.setTypeface(null, Typeface.NORMAL);
			view.setBackgroundColor(Color.WHITE);
		}

		@Override
		public View getItem(int index, View cachedView, ViewGroup parent) {
			currentItem = index;
			return super.getItem(index, cachedView, parent);
		}
	}

	private final int[] daysOfMonth = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31,
			30, 31 };

	// private void updateDayOfMonth() {
	// int positionMonth = month.getCurrentItem();
	// int totalDayOfMonth = daysOfMonth[positionMonth];
	// CalendarUtil calendarUtil = new CalendarUtil();
	// if (calendarUtil
	// .checkLeap(Integer.parseInt(years[year.getCurrentItem()]))) {
	// if (positionMonth == 1) {
	// totalDayOfMonth++;
	// }
	// }
	// int current_day_select = day.getCurrentItem();
	// if ((current_day_select + 1) > totalDayOfMonth) {
	// current_day_select = totalDayOfMonth - 1;
	// }
	// day.setCurrentItem(current_day_select);
	// updateDays(year, month, day);
	// }

	private void updateStatus() {
		int positionYear1 = year1.getCurrentItem();
		valueYear1 = years1[positionYear1];

		int positionYear2 = year2.getCurrentItem();
		valueYear2 = years2[positionYear2];

		// int positionDay = day.getCurrentItem();
		// valueDay = days[positionDay];

		int positionGioiTinh = gioitinh.getCurrentItem();
		posGioiTinh = positionGioiTinh;
	}

	private String[] getListString(int minValue, int maxValue) {
		int sizeDate = maxValue - minValue;
		String[] lists = new String[sizeDate];
		for (int i = 0; i < sizeDate; i++) {
			lists[i] = String.valueOf(minValue + i);
		}
		return lists;
	}
}
