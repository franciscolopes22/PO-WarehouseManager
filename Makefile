all:
	(cd po-uilib; make all)
	(cd ggc-core; make all)
	(cd ggc-app; make all)

clean:
	(cd po-uilib; make clean)
	(cd ggc-core; make clean)
	(cd ggc-app; make clean)