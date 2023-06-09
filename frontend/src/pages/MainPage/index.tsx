/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect } from 'react';
import { useGetSocialAnalysisQuery } from '@/apis/analyze';
import { useGetHotKeywordsQuery, useGetRelatedKeywordsQuery } from '@/apis/keyword';
import { SearchBar } from '@/components/molecules';
import { HotKeywords, NoBookmark, DailyAnalysis } from '@/components/organisms/MainPage';
import HotKeywordsSkeleton from '@/components/organisms/MainPage/HotKeywords/Skeleton';
import { useGetBookmarkQuery } from '@/apis/member';
import { getToken } from '@/utils/token';
import { getDateToYYYYDDMM, getOneDaysAgoDate, getOneMonthAgoDate } from '@/utils/date';
import * as S from './index.styles';
import DailyAnalysisSkeleton from '@/components/organisms/MainPage/DailyAnalysis/Skeleton';

const MainPage = () => {
  const token = getToken();
  // const dispatch = useAppDispatch();
  const {
    data: bookmark,
    error: bookmarkError,
    isLoading: bookmarkLoading,
    isSuccess: bookmarkSuccess,
  } = useGetBookmarkQuery(undefined, { refetchOnMountOrArgChange: true, skip: !token });

  const {
    data: hotKeywords,
    error: hotKeywordsError,
    isLoading: hotKeywordsLoading,
  } = useGetHotKeywordsQuery(undefined, {
    refetchOnMountOrArgChange: true,
  });

  const {
    data: socialAnalysis,
    error: socialAnalysisError,
    isLoading: socialAnalysisLoading,
  } = useGetSocialAnalysisQuery(
    {
      keyword: bookmarkSuccess ? bookmark!.bookmark : '',
      startDate: getDateToYYYYDDMM(getOneMonthAgoDate()),
      endDate: getDateToYYYYDDMM(getOneDaysAgoDate()),
    },
    {
      refetchOnMountOrArgChange: true,
      skip: !bookmarkSuccess,
    }
  );

  const {
    data: relatedKeywords,
    error: relatedKeywordsError,
    isLoading: relatedKeywordsLoading,
  } = useGetRelatedKeywordsQuery(
    {
      keyword: bookmarkSuccess ? bookmark!.bookmark : '',
    },
    {
      refetchOnMountOrArgChange: true,
      skip: !bookmarkSuccess,
    }
  );

  function a() {
    if (token) {
      if (socialAnalysis && relatedKeywords) {
        return (
          <DailyAnalysis
            keyword={bookmark!.bookmark}
            socialAnalysis={socialAnalysis!}
            relatedKeywords={relatedKeywords!}
          />
        );
        // eslint-disable-next-line no-else-return
      } else {
        return <DailyAnalysisSkeleton keyword="키워드" />;
      }
    }
    // eslint-disable-next-line no-useless-return, consistent-return
    return;
  }
  return (
    <S.Wrapper>
      <SearchBar placeholder="키워드를 입력하세요" />

      {hotKeywordsLoading && (
        <S.HotKeywordsWrapper>
          <HotKeywordsSkeleton />
          <HotKeywordsSkeleton />
        </S.HotKeywordsWrapper>
      )}

      {hotKeywords && (
        <S.HotKeywordsWrapper>
          <HotKeywords type="day" ranking={hotKeywords?.day} />
          <HotKeywords type="week" ranking={hotKeywords?.week} />
        </S.HotKeywordsWrapper>
      )}

      {!token && !bookmark && <NoBookmark />}

      {a()}
    </S.Wrapper>
  );
};

export default MainPage;
