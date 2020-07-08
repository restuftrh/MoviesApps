package com.MovieApps.util.recyclerviews;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

public class RecyclerViewAttacher {

    public static RecyclerAttacherBuilder with(RecyclerView recyclerView) {
        return new RecyclerAttacherBuilder(recyclerView);
    }

    public static class RecyclerAttacherBuilder {

        private RecyclerView recyclerView;
        private RecyclerView.LayoutManager layoutManager;
        private RecyclerView.Adapter adapter;
        private RecyclerView.ItemDecoration itemDecoration;

        private boolean pullToRefreshEnabled;
        private boolean hasFixedSize;

        RecyclerAttacherBuilder(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
            hasFixedSize = true;
        }

        public RecyclerAttacherBuilder hasFixedSize(boolean hasFixedSize) {
            this.hasFixedSize = hasFixedSize;
            return this;
        }

        public RecyclerAttacherBuilder layoutManager(RecyclerView.LayoutManager manager) {
            this.layoutManager = manager;
            return this;
        }

        public RecyclerAttacherBuilder adapter(RecyclerView.Adapter adapter) {
            this.adapter = adapter;
            return this;
        }

        public RecyclerAttacherBuilder itemDecoration(RecyclerView.ItemDecoration itemDecoration) {
            this.itemDecoration = itemDecoration;
            return this;
        }

        public RecyclerAttacherBuilder withPullToRefreshEnabled(boolean isEnabled) {
            this.pullToRefreshEnabled = isEnabled;
            return this;
        }

        public void attach() {
            if (layoutManager == null) {
                layoutManager = new LinearLayoutManager(recyclerView.getContext());
            }
            if (itemDecoration != null) recyclerView.addItemDecoration(itemDecoration);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(hasFixedSize);
            recyclerView.setAdapter(adapter);

            if (recyclerView instanceof XRecyclerView) {
                XRecyclerView xRecyclerView = (XRecyclerView) recyclerView;
                xRecyclerView.setPullRefreshEnabled(pullToRefreshEnabled);
            }
        }
    }
}
