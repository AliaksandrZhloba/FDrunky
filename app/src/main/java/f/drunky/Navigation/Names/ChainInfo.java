package f.drunky.Navigation.Names;

/**
 * Created by AZhloba on 12/20/2017.
 */

public class ChainInfo {
    public String chain;
    public int menuId;
    public int titleId;
    public String startView;

    public static ChainInfo create(String chain, int menuId, int titleId, String startView) {
        ChainInfo info = new ChainInfo();
        info.chain = chain;
        info.menuId = menuId;
        info.titleId = titleId;
        info.startView = startView;

        return info;
    }
}
