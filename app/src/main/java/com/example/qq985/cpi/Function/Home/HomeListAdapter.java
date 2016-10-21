package com.example.qq985.cpi.Function.Home;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.qq985.cpi.R;
import com.example.qq985.cpi.ViewPagerAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by qq985 on 2016/10/3.
 */

public class HomeListAdapter extends BaseAdapter implements Serializable, ViewPager.OnPageChangeListener {

    private List<HomeListItem> list;
    private Context context;

    public HomeListAdapter(List<HomeListItem> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if (list != null && position < list.size()) {
            return list.get(position).getType();
        }
        return super.getItemViewType(position);
    }

    @Override
    public int getViewTypeCount() {
        return HomeListItem.TYPE_COUNT;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int type = getItemViewType(position);
        switch (type) {
            case HomeListItem.TYPE_ARTICLE:
                HomeListAdapter.HAViewHolder view_holder;
                if (convertView == null) {
                    convertView = LayoutInflater.from(context).
                            inflate(R.layout.home_list_view_cell, null);

                    view_holder = new HomeListAdapter.HAViewHolder();

                    view_holder.title_tv
                            = (TextView) convertView.findViewById(R.id.tv_home_list_hint);
                    view_holder.iv_art_pic
                            = (ImageView) convertView.findViewById(R.id.iv_home_lv_pic);

                    view_holder.title_tv
                            .setText(list.get(position).getHomeArticleInfo().getTitle());
                    view_holder.iv_art_pic
                            .setImageBitmap(list.get(position).getHomeArticleInfo().getCachedBitmap().getBitmap());


                    convertView.setTag(view_holder);

                } else {
                    view_holder = (HomeListAdapter.HAViewHolder) convertView.getTag();
                    view_holder.title_tv.setText(list.get(position).getHomeArticleInfo().getTitle());
                    view_holder.iv_art_pic.setImageBitmap(list.get(position).getHomeArticleInfo().getCachedBitmap().getBitmap());

                }
                break;
            case HomeListItem.TYPE_VIEW_PAGER:
                System.out.println("有ViewPager");
                final HomeListAdapter.HViewPagerHolder hViewPagerHolder;
//                if (convertView == null) {
                convertView = LayoutInflater.from(context).
                        inflate(R.layout.home_view_pager_cell, null);

                hViewPagerHolder = new HomeListAdapter.HViewPagerHolder();

                hViewPagerHolder.initView(context, convertView, list.get(position).getHomeViewPagerItems());
                hViewPagerHolder.initPoint();

                convertView.setTag(hViewPagerHolder);

//                } else {
//                    hViewPagerHolder = (HomeListAdapter.HViewPagerHolder) convertView.getTag();
//                    LayoutInflater mLi = LayoutInflater.from(context);
//                    hViewPagerHolder.view1 = mLi.inflate(R.layout.home_viewpager01, null);
//                    hViewPagerHolder.view2 = mLi.inflate(R.layout.home_viewpager02, null);
//                    hViewPagerHolder.view3 = mLi.inflate(R.layout.home_viewpager03, null);
//                    hViewPagerHolder.view4 = mLi.inflate(R.layout.home_viewpager04, null);
//
//                    // 实例化ArrayList对象
//                    hViewPagerHolder.views = new ArrayList<View>();
//                    // 将要分页显示的View装入数组中
//                    hViewPagerHolder.views.add(hViewPagerHolder.view1);
//                    hViewPagerHolder.views.add(hViewPagerHolder.view2);
//                    hViewPagerHolder.views.add(hViewPagerHolder.view3);
//                    hViewPagerHolder.views.add(hViewPagerHolder.view4);

                // 设置监听
//                    hViewPagerHolder.viewPager.setOnPageChangeListener(this);
//                    // 设置适配器数据
//                    hViewPagerHolder.viewPager.setAdapter(new ViewPagerAdapter(hViewPagerHolder.views));
//                }
                break;
        }

        return convertView;
    }

    public void add(HomeListItem info) {
        list.add(info);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private static class HAViewHolder implements Serializable {
        TextView title_tv;
        ImageView iv_art_pic;
    }

    private static class HViewPagerHolder implements Serializable, ViewPager.OnPageChangeListener, View.OnClickListener {
        ViewPager viewPager;
        ArrayList<View> views;
        // 定义各个界面View对象
        View view1, view2, view3, view4;
        String[] str = new String[4];

        // 底部小点的图片
        ImageView[] points;
        TextView tv_view_pager_hint;
        // 记录当前选中位置
        int currentIndex;

        LinearLayout linearLayout;


        public void initView(final Context context, View convertView, HomeViewPagerItem[] homeViewPagerItems) {
            viewPager
                    = (ViewPager) convertView.findViewById(R.id.cell_home_view_pager);
            linearLayout
                    = (LinearLayout) convertView.findViewById(R.id.cell_ll_view_points);
            tv_view_pager_hint = (TextView) convertView.findViewById(R.id.cell_tv_home_viewpager_hint);

            initPoint();

            LayoutInflater mLi = LayoutInflater.from(context);
            view1 = mLi.inflate(R.layout.home_viewpager01, null);
            view2 = mLi.inflate(R.layout.home_viewpager02, null);
            view3 = mLi.inflate(R.layout.home_viewpager03, null);
            view4 = mLi.inflate(R.layout.home_viewpager04, null);

            Drawable drawable1 = new BitmapDrawable(homeViewPagerItems[0].getCachedBitmap().getBitmap());
            view1.setBackground(drawable1);
            Drawable drawable2 = new BitmapDrawable(homeViewPagerItems[1].getCachedBitmap().getBitmap());
            view2.setBackground(drawable2);
            Drawable drawable3 = new BitmapDrawable(homeViewPagerItems[2].getCachedBitmap().getBitmap());
            view3.setBackground(drawable3);
            Drawable drawable4 = new BitmapDrawable(homeViewPagerItems[3].getCachedBitmap().getBitmap());
            view4.setBackground(drawable4);


            view1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent_to_video=new Intent(context,HomeViewPagerVideo.class);
                    context.startActivity(intent_to_video);
                }
            });

            view2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("viewpager2 clicked");
                    Bundle bundle = new Bundle();
                    bundle.putString("author", "佚名");
                    bundle.putString("title", "的士司机的经济学");
                    bundle.putString("date", "10/01");
                    bundle.putString("abstract", "    生活中的经济学无处不在，三百六十行中行行皆蕴含着经济学的原理，本文以一位的士司机的经历告诉读者保持一份积极乐观的心态和一种善于观察细节总结经验的工作状态的重要性，文笔平实直白，读来耐人寻味……");
                    bundle.putString("abstractSectionPicUrl", "http://a3.topitme.com/9/16/e5/11756045494bae5169l.jpg");
                    bundle.putString("firstSection", "　　我要从徐家汇赶去机场，于是匆匆结束了一个会议，在美罗大厦前搜索出租车。一辆大众发现了我，非常专业的、径直的停在我的面前。这一停，于是有了后面的这个让我深感震撼的故事，象上了一堂生动的MBA案例课。为了忠实于这名出租车司机的原意，我凭记忆尽量重复他原来的话。\n" +
                            "　　“去哪里……好的，机场。我在徐家汇就喜欢做美罗大厦的生意。这里我只做两个地方。美罗大厦，均瑶大厦。你知道吗？接到你之前，我在美罗大厦门口兜了两圈，终于被我看到你了！从写字楼里出来的，肯定去的不近~~~”\n" +
                            "　　“哦？你很有方法嘛！”我附和了一下。\n" +
                            "　　“做出租车司机，也要用科学的方法。”他说。我一愣，顿时很有些兴趣“什么科学的方法？”\n" +
                            "　　“要懂得统计。我做过精确的计算。我说给你听啊。我每天开17个小时的车，每小时成本34.5元……”\n" +
                            "　　“怎么算出来的？”我追问。\n" +
                            "　　“你算啊，我每天要交380元，油费大概210元左右。一天17小时，平均每小时固定成本22元，交给公司，平均每小时12.5元油费。这是不是就是34.5元？”，我有些惊讶。我打了10年的车，第一次听到有出租车司机这么计算成本。以前的司机都和我说，每公里成本0.3元，另外每天交多少钱之类的。\n" +
                            "　　“成本是不能按公里算的，只能按时间算。你看，计价器有一个“检查”功能。你可以看到一天的详细记录。我做过数据分析，每次载客之间的空驶时间平均为7分钟。如果上来一个起步价，10元，大概要开10分钟。也就是每一个10元的客人要花17分钟的成本，就是9.8元。不赚钱啊！如果说做浦东、杭州、青浦的客人是吃饭，做10元的客人连吃菜都算不上，只能算是撒了些味精。”\n" +
                            "　　强！这位师傅听上去真不象出租车司机，到象是一位成本核算师。“那你怎么办呢？”我更感兴趣了，继续问。看来去机场的路上还能学到新东西。");
                    bundle.putString("firstSectionPicUrl", "http://a4.topitme.com/l/201011/12/12895372223296.jpg");
                    bundle.putString("secondSection", "　“千万不能被客户拉了满街跑。而是通过选择停车的地点，时间，和客户，主动地决定你要去的地方。”我非常惊讶，这听上去很有意思。“有人说做出租车司机是靠运气吃饭的职业。我以为不是。你要站在客户的位置上，从客户的角度去思考。”这句话听上去很专业，有点象很多商业管理培训老师说的“put yourself into others\" shoes.”\n" +
                            "　　“给你举个例子，医院门口，一个拿着药的，一个拿着脸盆的，你带哪一个。”我想了想，说不知道。\n" +
                            "　　“你要带那个拿脸盆的。一般人病小痛的到医院看一看，拿点药，不一定会去很远的医院。拿着脸盆打车的，那是出院的。住院哪有不死人的？今天二楼的谁死了，明天三楼又死了一个。从医院出来的人通常会有一种重获新生的感觉，重新认识生命的意义，健康才最重要。那天这个说：走，去青浦。眼睛都不眨一下。你说他会打车到人民广场，再去做青浦线吗？绝对不会！”\n" +
                            "　　我不由得开始佩服。\n" +
                            "　　“再给你举个例子。那天人民广场，三个人在前面招手。一个年轻女子，拿着小包，刚买完东西。还有一对青年男女，一看就是逛街的。第三个是个里面穿绒衬衫的，外面羽绒服的男子，拿着笔记本包。我看一个人只要3秒钟。我毫不犹豫地停在这个男子面前。这个男的上车后说：延安高架、南北高架~~~还没说后面就忍不住问，为什么你毫不犹豫地开到我面前？前面还有两个人，他们要是想上车，我也不好意思和他们抢。我回答说，中午的时候，还有十几分钟就1点了。那个女孩子是中午溜出来买东西的，估计公司很近；那对男女是游客，没拿什么东西，不会去很远；你是出去办事的，拿着笔记本包，一看就是公务。而且这个时候出去，估计应该不会近。那个男的就说，你说对了，去宝山。”");
                    bundle.putString("secondSectionPicUrl", "http://a4.topitme.com/l/201010/27/12881775202400.jpg");
                    bundle.putString("thirdSection", "　　“那些在超市门口，地铁口打车，穿着睡衣的人可能去很远吗？可能去机场吗？机场也不会让她进啊。”\n" +
                            "　　有道理！我越听越有意思。\n" +
                            "　　“很多司机都抱怨，生意不好做啊，油价又涨了啊，都从别人身上找原因。我说，你永远从别人身上找原因，你永远不能提高。从自己身上找找看，问题出在哪里。”这话听起来好熟，好像是“如果你不能改变世界，就改变你自己”，或者Steven Corvey的“影响圈和关注圈”的翻版。“有一次，在南丹路一个人拦车，去田林。后来又有一次，一个人在南丹路拦车，还是去田林。我就问了，怎么你们从南丹路出来的人，很多都是去田林呢？人家说，在南丹路有一个公共汽车总站，我们都是坐公共汽车从浦东到这里，然后搭车去田林的。我恍然大悟。比如你看我们开过的这条路，没有写字楼，没有酒店，什么都没有，只有公共汽车站，站在这里拦车的多半都是刚下公共汽车的，再选择一条最短路经打车。在这里拦车的客户通常不会高于15元。”\n" +
                            "　　“所以我说，态度决定一切！”我听十几个总裁讲过这句话，第一次听出租车司机这么说。\n" +
                            "　　“要用科学的方法，统计学来做生意。天天等在地铁站口排队，怎么能赚到钱？每个月就赚500块钱怎么养活老婆孩子？这就是在谋杀啊！慢性谋杀你的全家。要用知识武装自己。学习知识可以把一个人变成聪明的人，一个聪明的人学习知识可以变成很聪明的人。一个很聪明的人学习知识，可以变成天才。”");
                    bundle.putString("thirdSectionPicUrl", "http://a4.topitme.com/l/201007/31/12805889724261.jpg");
                    bundle.putString("fourthSection", "　　“有一次一个人打车去火车站，问怎么走。他说这么这么走。我说慢，上高架，再这么这么走。他说，这就绕远了。我说，没关系，你经常走你有经验，你那么走50块，你按我的走法，等里程表50块了，我就翻表。你只给50快就好了，多的算我的。按你说的那么走要50分钟，我带你这么走只要25分钟。最后，按我的路走，多走了4公里，快了25分钟，我只收了50块。乘客很高兴，省了10元钱左右。这4公里对我来说就是1块多钱的油钱。我相当于用1元多钱买了25分钟。我刚才说了，我一小时的成本34.5块，我多合算啊！”\n" +
                            "　　“在大众公司，一般一个司机3、4千，拿回家。做的好的大概5千左右。顶级的司机大概每月能有7000。全大众2万个司机，大概只有2-3个司机，万里挑一，每月能拿到8000以上。我就是这2-3个人中间的一个。而且很稳定，基本不会大的波动。”\n" +
                            "　　太强了！到此为止，我越来越佩服这个出租车司机。\n" +
                            "　　“我常常说我是一个快乐的车夫。有人说，你是因为赚的钱多，所以当然快乐。我对他们说，你们正好错了。是因为我有快乐、积极的心态，所以赚的钱多。”\n" +
                            "　　说的多好啊！\n" +
                            "　　“要懂得体味工作带给你的美。堵在人民广场的时候，很多司机抱怨，又堵车了！真是倒霉。千万不要这样，用心体会一下这个城市的美，外面有很多漂亮的女孩子经过，非常现代的高楼大厦，虽然买不起，但是却可以用欣赏的眼光去享受。开车去机场，看着两边的绿色，冬天是白色的，多美啊。再看看里程表，100多了，就更美了！每一样工作都有她美丽的地方，我们要懂得从工作中体会这种美丽。”\n" +
                            "　　“我10年前是强生公司的总教练。8年前在公司作过三个不同部门的部门经理。后来我不干了，一个月就3、5千块，没意思。就主动来做司机。我愿意做一个快乐的车夫。哈哈哈哈。”\n" +
                            "　　到了机场，我给他留了一张名片，说：“你有没有兴趣这个星期五，到我办公室，给微软的员工讲一讲你怎么开出租车的？你就当打着表，60公里一小时，你讲多久，我就付你多少钱。给我电话。”\n" +
                            "　　我迫不及待的在飞机上记录下他这堂生动的MBA课。");
                    bundle.putString("fourthSectionPicUrl", "http://a4.topitme.com/l/201005/10/12734805045677.jpg");
                    Intent intent_to_details = new Intent(context, HomeArticleDetails.class);
                    intent_to_details.putExtras(bundle);
                    context.startActivity(intent_to_details);
                }
            });


            view3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("viewpager3 clicked");
                    Bundle bundle = new Bundle();
                    bundle.putString("author", "佚名");
                    bundle.putString("title", "公立医院私有化还要多久");
                    bundle.putString("date", "10/02");
                    bundle.putString("abstract", "作为本届政府上台执政的重要政治资本之一，以基本医疗制度为主要内容的第一阶段医疗改革在本届政府上台之初曾短暂受到表扬。但随着2013年一句\"医改尚未进入深水区\"的论断，批评声渐起：认为过去四年的大规模投入，仍未改变\"看病贵、看病难\"疾。");
                    bundle.putString("abstractSectionPicUrl", "http://a3.topitme.com/6/0f/df/1175690272999df0f6l.jpg");
                    bundle.putString("firstSection", "要讨论公立医院私有化这一命题，必须先来看一看过去5年中国政府在医改方面都做了哪些工作。\n" +
                            "2008年，时任国务院副总理的李克强被任命为新成立的国务院深化医药卫生体制改革领导小组（医改组）组长。\n" +
                            "从2009到2011年，在这位副总理的主导下，国家对医疗产业一共投入了近8500亿人民币，其主要投向是新农合。截止目前，全国医保的覆盖率上升至95%以上。虽然报销水平仍有待提高，但好歹完成了全民覆盖。城乡居民都有相对公平的医疗保险。此前一直饱受国际舆论诟病的医疗服务不公平的弊病从制度上得以基本消除。\n" +
                            "　　随着基本药物制度和基本医疗保险制度的建立，大框架已经差不多。此前呼声最高的改革区域是\"医药分离\"，但最近，国务院却倡导社会资本进医院，与舆论呼声不尽一致。最近一两年，引入社会资本办医院的执行，大有从\"社会资本新办医院\"转向\"公立医院私有化\"（政府直接售卖医院）的迹象。这一转变，又恰巧发生在地方政府债务高企、财政周转困难的时点，不由引人联想。到底公立医院私有化是为了什么？我们先把过去数年医改走过的路详加审查。");
                    bundle.putString("firstSectionPicUrl", "http://a3.topitme.com/a/e0/80/11756902706dc80e0al.jpg");
                    bundle.putString("secondSection", "否则一切医改都会失败。改革的范畴从来就是两块：价格和产权。但在改革医疗产业价格之前，必须先建立覆盖全民的医保。医保通过全民众筹，会大大降低个体所承担的风险。进入政府医保，个体就基本免受价格、产权改革可能引起的负面影响。此时进行价格、产权改革，可保群众无虞。因此，全民医保是改革的准备，不做不行。国家过去4年所投入的8500亿元，绝大多数流向了这块。即使刺激、释放了医疗服务需求，导致看病更难，但这样的安排是必要的，毋庸置疑。\n" +
                            "　　有了医保制度，就不得不考虑经济账。医疗费用的预期水平，也就是GDP的6%-7%，我们距离这个水平，也就1-2个百分点左右（按2013年GDP，两个百分点大约是1万亿）。饼就只能做这么大，怎么分配，是个大问题。药费一直是我国医疗费用中的大头，所以国家同时建立了基药制度，把常用药的价格降下来，把药品的份额减下来。但粗暴降价并非良方。降价会强烈抑制供给热情。近年来，\"低价好药消失\"、\"毒胶囊\"等医药事件，与此不无关系。实际上，前面已经论述过，抑制医疗供给绝不是好方子，改善医疗产业，只能管控需求，\n" +
                            "　　\"医药分离\"是另一个医改界的迷思，总觉得只要实现了这一伟大构想，药价就可以降下来，看病就可以不贵。但笔者窃认为，这个提法是舆论引导，且是不怀好意、蓄意转移注意力的舆论引导，因为医药分离与药价降低之间根本没有任何关系。现在药价虚高是因为医生的薪酬太低、不得不通过开\"大处方\"拿回扣，是因为医药商业公司能够统计处方、从而准确计算回扣，是因为政府审核、分配医保药品享有巨大的寻租空间。这三点，都跟医药分离没有半点关系。");
                    bundle.putString("secondSectionPicUrl", "http://a3.topitme.com/3/3a/d1/1175690269ff9d13a3l.jpg");
                    bundle.putString("thirdSection", "　怎么解决药费高？还是得从上述三个环节出发。首先，我们不得不承认，寻租空间几乎无法消除。医保不可能覆盖所有药品，所以医保药品的政府审核不可避免，因此寻租在所难免。我们可以设计机制，监督、惩罚，但只要有政府的审核、分配，寻租就不可能被完全消除（安妮·克鲁格等人建立了一整套寻租理论，在此不赘述）。其次，阻止医药商业公司统计处方，只是隔靴搔痒。神通广大的代理商总能通过某些方法，得知医生的处方量。所以问题的症结，还是医生的薪酬和\"大处方\"。\n" +
                            "　　医生的薪酬其实是医疗服务价格，\"大处方\"是由于医生自主权太大，滥开药。可喜的是，这两个问题，实际上可以通过一个问题而一箭双雕：医疗价格制度。医疗产业改革良久，真正的深水区，是价格改革，这才是医疗产业利益绑定的核心抓手。医疗服务价格的重新拟定，其核心是成本的精确认定。但可悲的是，由于公立医院财政补贴制度的设计缺陷，全国范围内的公立医院运营的账面成本是严重失真的！没有一个公立医院会把真实成本展现出来，因为那样得到的财政补贴更少。怎么解决这一问题？取消财政补贴的方法显然太简单粗暴，会引起整个产业的剧烈震荡；妙法在于另一块改革区：产权改革。\n" +
                            "　　我们观察到的公开资料显示，金陵药业（宿迁医院68172万收入、8340万净利润；仪征医院20960万收入、7845万净利润）等社会资本控股的医院（并非高端医院，而是综合性医院）是大幅盈利的。其原因在于，医院运营纳入到以盈利为目标的社会资本中后，对报表业绩的追求就会显现，因此这些医院的成本大幅下降，真实的利润水落石出。通过产权改革，倒逼真实成本，从而渐进价格改革；这才是本轮医改的主题——公立医院私有化的真实目的！");
                    bundle.putString("thirdSectionPicUrl", "http://img2.soyoung.com/hospital2/20131201/7/20131201154855643.jpg");
                    bundle.putString("fourthSection", "　现实的医改路径可能是：待产权改革到一定阶段，以DRG(Diagnosis Related Group)为目标的价格改革就会渐行渐近。通俗的讲，DRG是一种包干式的价格、支付制度；对每一个病人住院治疗，医保部门只根据其诊断的病症向医院支付一定的费用（这个费用水平对于区域内的所有医院一样），无论医院最终为这个病人花费了多少。在DRG价格体系的经营压力下，对治疗手段、处方进行全面管理的临床路径(Clinical Pathway，和行医指南异曲同工)势在必行，药品消费将从医药的利润中心变为成本中心，大幅改善滥开药、\"大处方\"的问题。医院的竞争将从大药方竞赛变为运营效率竞赛，规范医疗行为，提高效率、控制费用。\n" +
                            "　　医疗产业政策是个政治、经济、政府事务、市场机制等犬牙交错的问题，涉及面之广、利益环节之多，都是其他产业难以想象的。从医疗保险、医院运营、支付体系、创新激励到看门人制度和双向转诊，个中关节环环相扣，就像牵一发而动全身的蜘蛛网。国家和经济社会为提供一个可以操作的医疗产业运转体系，煞费苦心。医疗改革之路还有很长，每一步前进都会引起产业资源的再调整。在这样的改革背景下如何进行投资医疗产业，是更值得深思的命题。");
                    bundle.putString("fourthSectionPicUrl", "http://img1.imgtn.bdimg.com/it/u=429043373,2594169908&fm=21&gp=0.jpg");
                    Intent intent_to_details = new Intent(context, HomeArticleDetails.class);
                    intent_to_details.putExtras(bundle);
                    context.startActivity(intent_to_details);
                }
            });

            view4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("viewpager4 clicked");
                    Bundle bundle = new Bundle();
                    bundle.putString("author", "佚名");
                    bundle.putString("title", "买房癌，一种中国人的不治之症");
                    bundle.putString("date", "10/03");
                    bundle.putString("abstract", "“假离婚”现象既是经济问题，也是社会问题了。这说明了高房价已经使市民闻之色变，不管是否买得起房，都想尽办法多买。也是社会问题，很多人把婚姻当成儿戏，况且假离婚带来的风险太高。更是全民加杠杆的最后欢腾。因为大量无力购房的群体入市场，居民房贷的风险开始攀升。");
                    bundle.putString("abstractSectionPicUrl", "http://iphone.tgbus.com/UploadFiles/201503/2015032010362823.jpg");
                    bundle.putString("firstSection", "这几个星期在魔都，爱情有了新标准，敢离婚的才是真爱。于是你看到一大堆人赶着去离婚，逼得政局的都不限号了，上午40个，下午40个。这股离婚潮是被一个“9月初上海将施行最严限购政策”的谣言煽动起来的。其中的逻辑关系大概就是离了婚方有可能买上房，多买房就意味着发了财，因为“京沪房价永远涨”。 \n" +
                            "　　一位去了某民政局的朋友这么写道：“有的是父母带着孩子，有的是三代同堂来离婚，所有人脸上洋溢着对未来的憧憬与期待。” 　能为了买房把离婚演绎到如此魔幻现实的地步，实在只能说是中国人特别会苦中作乐。 \n" +
                            "　　从消费体验来讲，大概没有比在中国买房更糟糕的。但即便如此，大家还是前赴后继。房子变成了一切的中心，结婚、生子、工作、看病、10年后的同学会、人生评价都和房子紧紧绑在一起。 农耕民族也不止我们一个，家庭观念大家也都很重，但那种把一切都换算成房子的热情，别处真找不到，即使只有70年保质期也挡不住。隔壁日本的建筑设计师们已经开始宣扬“旅人与住民的界限开始模糊”，但我们不行，诗和远方有没有另说，房子是一定要买的，这也只有真爱能解释了 —— 说到底，跟打牌一样，骨子里就是想当地主。 \n" +
                            "但问题是，这日子也太畸形了。一家六口深居简出节衣缩食背着几套房子月供十万元；中小企业老板关停工厂炒房；留美博士被嘲笑出国10年不如买房。说好的实业兴国，知识就是力量呢？\n" +
                            "　归根结底一句话，干啥都不如买房。一开始可能还当作笑话，后来发现无法反驳，如今内心似乎有点小认同 。\n" +
                            "　这时候，面对买房这个大魔王，我们其实也该有点福原爱的屡败屡战精神呢。买房是为了住，赚钱是为了花，可别本末倒置了呀。而且，时间还长着，中国房地产市场不过三四十年，你还有更多日子要过。大千世界有各种各样的精彩，年轻的心全都执着在买房这件事上实在有点可惜啊。 ");
                    bundle.putString("firstSectionPicUrl", "http://a3.topitme.com/4/00/45/117569026354a45004l.jpg");
                    bundle.putString("secondSection", "我们为什么这么迷恋买房？\n" +
                            "　　新时代下，总有逼着你非得买房的理由，比如婚房和学区房。相亲角的海报上，不提房子就等于简历里没留电话号码一样，系统自动剔除。小学教育多重要呀，孩子接触的第一批同龄人决定了他／她人生的高度！只要能在好学区，房子没厕所也可以。摘录一位在美国工作安家的网友的微博：“我让我爸等那套学区房涨到×万元就出手，他不肯，说以后我有了儿子可以回国落户口，上一附小，学区房不能浪费了。话说回来，婚房、学区房，说到底和门当户对、孟母三迁也没本质区别，看来“买房病”还真是与时俱进生生不息。 \n" +
                            "房价真的没跌过呀\n" +
                            "　　国家政府很早之前就说过放假会跌下来，时光荏苒，“泡沫”依旧，遏制也不是一天两天，但房价仍然是一路攀登。而那些没啥长处的人确实凭借炒房财富增值，这比1000条有理有据可以服人的“房价必跌”分析更深入人心。人们硬要相信房价和钻石一样永远，也没太多办法劝。话说回来，别的投资有多作死大家是领教过的，房子可能还真是值得托付的那个？毕竟我们信奉实践是检验真理的唯一标准。 ");
                    bundle.putString("secondSectionPicUrl", "http://a3.topitme.com/d/4a/e7/11756902648f6e74adl.jpg");
                    bundle.putString("thirdSection", "实在是租房体验这么糟\n" +
                            "也就是想要个舒服落脚的地方，可租房实在太苦。如果能租到干净安全、水电煤Wi-Fi都好、物业给力、租金10年稳定的普通房子，谁不想像《老友记》一样一辈子租房住。但现在的租房经历实在太容易让人产生身处大城市的漂泊无助感了。好不容易租到个好房子，一想到每月上供的租金是在帮别人还贷，怒从心中起，也是可以理解。大家都是有点小讲究的年轻人，买房，只是想住得好点而已。 \n" +
                            "从众、从心\n" +
                            "　　买房和结婚应该算是年轻人最常被催的两个问题了。如果不上网不刷朋友圈不和亲戚朋友聊天，很多人都还能理性地去看待。可一旦走亲串友或是去同学会上坐一会儿，架不住氛围一团火，就怂了：闲钱有一些，但不够，也不是没房住，改善下当然好，这么涨不合理啊肯定会跌，再等等吧 —— 哎呀，大家都买了再不买不行了。即使觉得这小船迟早要翻，反正大家都在，就you jump I jump了。\n" +
                            "买房主体的闹剧\n" +
                            "据说，上海的现行政策是这么规定的：单身非上海户籍人员不能买房。一位外地有为青年，爱上了一位可爱的上海姑娘。要娶我女儿先买房，准丈母娘说；要买房先结婚，工作人员说……一种死循环。逻辑上大概可以再找个人假结婚吧。“亲爱的，为了娶你，我先娶她。”假离婚网上有高人详解了一对夫妻如何在上海的限购政策下买12套房。截取一些步骤：“和公公结婚”“和丈母娘离婚”“找个外人结婚”……太玄妙了，这内功心法。总之如果能练成此功，1亿元的小目标也能完成啦。 ");
                    bundle.putString("thirdSectionPicUrl", "http://img.sccnn.com/bimg/337/43513.jpg");
                    bundle.putString("fourthSection", "房价跌了要闹事\n" +
                            "　　也有房产商想动用下市场规律让房价有涨有跌，可房主们不答应。（我买的）房，怎么能跌呢？什么愿赌服输契约精神都忘了，杠铃砸我身上，就是杠铃故意，要我命的坏蛋。。 　冰冰早就教导过我们：“买得起多贵的房，吃得起多大的亏。” \n" +
                            "买房还是买菜呢？\n" +
                            "　　不止一位朋友介绍了这样的买房过程：看中的房子半小时一个价，一分钟不敢耽搁，付了定金房东也能立马不认账，必须马上签合同，突然多要20万元也得咬牙，不管这是几十个月的工资。几分钟里挥斥数百万元，大概是平生离老派华尔街交易员最近的时刻。只不过那种消费体验好像也不会有买买买的满足感。虽然不求头等舱式的贴心服务，好歹别像大清早超市里抢特价鸡蛋那样OK？ \n" +
                            "买房上瘾\n" +
                            "买房大概算是个大型游戏。那些通关了的玩家有些成就感也正常，但有些人就上瘾了。每天早上起来刷一遍互助卖房的微博，周末没事干去楼盘散心，每天盘算着自己怎么还能弄出个购房资格，如何把亲戚的房子再倒腾出利用价值。当然啦，只要不影响别人，有这样的大型爱好也没啥，开心就好。 \n" +
                            "买房固然很重要，但不要把买房当作人生唯一追求的事，买房其实是一件顺其自然的事。鲁迅先生曾说：辱骂和恐吓决不是战斗。那么套用一下就是：房价靠骂是骂不下来的，该干嘛就干嘛，所以房价问题就是心态问题，以一颗平常心看待它，也许就不会有这么多闹剧了。");
                    bundle.putString("fourthSectionPicUrl", "http://pic.58pic.com/58pic/13/84/67/32t58PICCbK_1024.jpg");
                    Intent intent_to_details = new Intent(context, HomeArticleDetails.class);
                    intent_to_details.putExtras(bundle);
                    context.startActivity(intent_to_details);
                }
            });

            str[0] = homeViewPagerItems[0].getStr();
            str[1] = homeViewPagerItems[1].getStr();
            str[2] = homeViewPagerItems[2].getStr();
            str[3] = homeViewPagerItems[3].getStr();


            // 实例化ArrayList对象
            views = new ArrayList<View>();
            // 将要分页显示的View装入数组中
            views.add(view1);
            views.add(view2);
            views.add(view3);
            views.add(view4);

            // 设置监听
            viewPager.addOnPageChangeListener(this);
            // 设置适配器数据
            viewPager.setAdapter(new ViewPagerAdapter(views));
        }

        /**
         * 初始化底部小点
         */
        public void initPoint() {

            points = new ImageView[4];

            // 循环取得小点图片
            for (int i = 0; i < points.length; i++) {
                // 得到一个LinearLayout下面的每一个子元素
                points[i] = (ImageView) linearLayout.getChildAt(i);
                // 默认都设为灰色
                points[i].setEnabled(true);
                // 给每个小点设置监听
//                points[i].setOnClickListener(this);
                // 设置位置tag，方便取出与当前位置对应
                points[i].setTag(i);
            }

            // 设置当面默认的位置
            currentIndex = 0;
            // 设置为白色，即选中状态
            points[currentIndex].setEnabled(false);
            tv_view_pager_hint.setText(str[0]);

        }

        /**
         * 滑动状态改变时调用
         */
        @Override
        public void onPageScrollStateChanged(int arg0) {
            System.out.println("onPageScrollStateChanged");
        }

        /**
         * 当前页面滑动时调用
         */
        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
//            System.out.println("onPageScrolled");
        }

        /**
         * 新的页面被选中时调用
         */
        @Override
        public void onPageSelected(int arg0) {
            // 设置底部小点选中状态
            System.out.println("onPageSelected");
            setCurDot(arg0);
        }

        @Override
        public void onClick(View v) {
            System.out.println("clickedclickedclickedclicked");
            int position = (Integer) v.getTag();
            setCurView(position);
            setCurDot(position);

        }

        /**
         * 设置当前页面的位置
         */
        public void setCurView(int position) {
            tv_view_pager_hint.setText(str[position]);
            viewPager.setCurrentItem(position);
        }

        /**
         * 设置当前的小点的位置
         */
        public void setCurDot(int position) {
            if (position < 0 || position > 4 - 1 || currentIndex == position) {
                return;
            }
            points[position].setEnabled(false);
            tv_view_pager_hint.setText(str[position]);
            points[currentIndex].setEnabled(true);

            currentIndex = position;
        }
    }

}
